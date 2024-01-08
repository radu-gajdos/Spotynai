# Documentation Spotynai

Spotynai este o aplicatie de tip web, care se bazeaza pe un REST-Controller prin intermediul caruia sunt posibile urmatoarele functionalitati: 

- CURD Operations
- Sistem de autentificare
- Sistem de notificari
- Functionalitate de undo la update-ul melodiilor

---

Pentru realizarea acestei aplicatie am folosit un tipar de tip MVC 🤷‍♂️.

Pentru a importa dependentele ne-am folosit de MAVEN.

Primul pas a fost sa definim modele(clasele) necesare, mai exact:

- Album
- Artist
- Genre
- Membership
- Notification
- Playlist
- Podcast
- Song
- User

Fiecare dintre aceste clase dispun de o cheie primara (adica unica) si atribute specifice lor

---

Dupa am definit relatiile dintre aceste modele, in functie de logica aplicatiei. De exemplu:

- relatie de tip 1:M intre Artist - Observer
    
    ```jsx
    @OneToMany(fetch = FetchType.EAGER)
        private List<UserEntity> observers;
    ```
    
- relatie de tip M:M intre Playlist-Song
    
    In Springboot relatia de M:M se realizaeza cu ajutorul unui tabel intermediar creat automat.
    
    ```jsx
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        private List<ArtistEntity> artistEntity;
    ```
    
- relatie de tip 1:1 intre User-Membership
    
    ```jsx
    @OneToOne(cascade = CascadeType.ALL)
        private MembershipEntity membershipEntity;
    ```
    

---

Pentru protectia datelor, am realizat obiecte de tip DTO (DATA TRANSFER OBJECT), iar la operarea cu DTO’s se foloseste un Mapper pentru schimbarea lor in actual Entities.

```jsx
//DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long id;

    private String name;

    private String bio;

    private List<SongDto> songDtos;
}
```

```jsx
//Entity
Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Arist")

public class ArtistEntity implements Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String name;

    private String bio;

    @OneToMany(fetch = FetchType.EAGER)
    private List<UserEntity> observers;

    @ManyToMany(mappedBy = "artistEntity", fetch = FetchType.EAGER)
    private List<SongEntity> songEntities;

    @Override
    public void addObserver(UserEntity user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(UserEntity user) {
        observers.remove(user);
    }

    @Override
    public void notifyObservers() {
        for (UserEntity observer : this.observers) {
            observer.update(this);
        }
    }

    public void addSong(SongEntity songEntity) {
        notifyObservers();
        this.songEntities.add(songEntity);
    }
}
```

```jsx
//partea de mapper
package com.map.Mappers;

public interface Mapper<A,B> {

    B mapTo(A a);

    A mapFrom(B b);

}

@Component
public class ArtistMapperImpl implements Mapper<ArtistEntity, ArtistDto> {

    private final ModelMapper modelMapper;

    public ArtistMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ArtistDto mapTo(ArtistEntity artistEntity) {
        return modelMapper.map(artistEntity, ArtistDto.class);
    }

    @Override
    public ArtistEntity mapFrom(ArtistDto artistDto) {
        return modelMapper.map(artistDto, ArtistEntity.class);
    }
}
```

---

Baza de date folosita este de tip `Postgres` 14-Alpine, accesata prin `Docker`.

---

Proiectul este structurat folosind o arhitectură tradițională de tip Controller-Repository-Service, care separă responsabilitățile codului.

### Componente Principale:

1. **Repository**:
    - Asigură accesul la date și interacțiunea cu baza de date.
    - Asigura metode pentru operațiile CRUD.
2. **Service**:
    - Conține logica aplicatiei și a procesarii datelor.
    - Interacționează cu repository pentru accesarea datelor și ajuta la modificarea informatiilor.
3. **Controller**:
    - Responsabil pentru gestionarea cererilor HTTP și delegarea logicii către servicii adecvate.
    - Implementează endpoint-urile aplicației și coordonează interacțiunea cu serviciile.

---

Exemple de cod si explicatii pentru Repository-Service-Controller:

```jsx
/**
 * Interfața UserRepo definește operațiile CRUD pentru entitatea UserEntity.
 * Această interfață extinde CrudRepositor care oferă metodele de bază pentru operațiile CRUD.
 */
package com.map.Repositories;

import com.map.Domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

    /**
     * Căută un utilizator după numele de utilizator și parolă.
     */
    UserEntity findByUsernameAndPassword(String username, String password);

    /**
     * Verifică dacă există un utilizator cu numele de utilizator specificat.
			*/
    boolean existsByUsername(String username);

    /**
     * Căută un utilizator după numele de utilizator.
     */
    UserEntity findByUsername(String username);
}
```

```java
/**
 * Implementarea serviciului UserService care gestionează operațiile legate de utilizatori.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    /**
     * Constructorul clasei care primește un repository de utilizatori ca dependență.
     */
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Creează un utilizator și salvează în baza de date.
     */
    @Override
    public UserEntity createUser(UserEntity userEntity) {
        UserProxy userProxy = new UserProxy(userEntity);
        userProxy.setBadge();
        return userRepo.save(userEntity);
    }

    /**
     * Returnează o listă cu toți utilizatorii din baza de date.
     */
    @Override
    public List<UserEntity> findAll() {
        return StreamSupport.stream(userRepo
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    /**
     * Găsește un utilizator după ID.
     */
    @Override
    public Optional<UserEntity> findOne(Long id) {
        return userRepo.findById(id);
    }

    /**
     * Verifică dacă un utilizator există după ID.
     */
    @Override
    public boolean isExists(Long id) {
        return userRepo.existsById(id);
    }

    /**
     * Șterge un utilizator după ID.
     */
    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    /**
     * Autentifică un utilizator după numele de utilizator și parolă.
     */
    @Override
    public boolean authenticateUser(String username, String password) {
        UserEntity user = userRepo.findByUsernameAndPassword(username, password);
        return user != null;
    }

    /**
     * Returnează ID-ul unui utilizator după numele de utilizator.
     */
    @Override
    public Long getUserIdByUsername(String username) {
        UserEntity user = userRepo.findByUsername(username);
        return user != null ? user.getId() : null;
    }

    /**
     * Verifică dacă un nume de utilizator este deja folosit.
     */
    @Override
    public boolean isUsernameTaken(String username) {
        return userRepo.existsByUsername(username);
    }

}
```

```java
/**
 * Controlerul UserController gestionează cererile HTTP legate de utilizatori.
 */
@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class UserController {

    @Autowired
    private Mapper<UserEntity, UserDto> userMapper;

    @Autowired
    private UserService userService;

    /**
     * Creează un nou utilizator.
     */
    @PostMapping(path = "/create_user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.mapFrom(userDto);
        UserEntity savedUserEntity = userService.createUser(userEntity);
        return userMapper.mapTo(savedUserEntity);
    }

    /**
     * Șterge un utilizator după ID.
     */
    @DeleteMapping(path = "/delete_user/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Returnează o listă cu toți utilizatorii.
     */
    @GetMapping(path = "/users")
    public List<UserDto> listUsers() {
        List<UserEntity> users = userService.findAll();
        return users.stream()
                .map(userMapper::mapTo)
                .collect(Collectors.toList());
    }

    /**
     * Returnează un utilizator după ID.
     */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id) {
        Optional<UserEntity> foundUser = userService.findOne(id);
        return foundUser.map(userEntity -> {
            UserDto userDto = userMapper.mapTo(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualizează un utilizator după ID.
     */
    @PutMapping(path = "/update_user/{id}")
    public ResponseEntity<UserDto> fullUpdate(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto) {
        Optional<UserEntity> existingUserOptional = userService.findOne(id);

        if (existingUserOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserEntity existingUser = existingUserOptional.get();
        userDto.setId(id);

        ObjectUpdater.updateFields(existingUser, userDto);

        UserEntity savedUserEntity = userService.createUser(existingUser);

        return new ResponseEntity<>(
                userMapper.mapTo(savedUserEntity), HttpStatus.OK);
    }
}
```

1. **POST**: Trimite date pentru a crea o resursă nouă.
2. **GET**: Solicită date de la un server.
3. **PUT**: Actualizează o resursă existentă sau creează una nouă.
4. **DELETE**: Șterge o resursă de pe server.

---

Am mai avut nevoie de Controllere suplimentare, cum ar fi:

1. LoginController
    
    ```java
    /**
     * Controller-ul LoginController gestionează cererile HTTP legate de autentificare și înregistrare.
     */
    @Controller
    public class LoginController {
    
        @Autowired
        private final UserService userService;
    
        /**
         * Constructorul clasei care primește un serviciu de utilizatori ca dependență.
         */
        public LoginController(UserService userService) {
            this.userService = userService;
        }
    
        /**
         * Procesează cererea POST pentru autentificare.
         * @return Numele paginii sau redirecționarea către pagina de start în caz de autentificare reușită.
         */
        @PostMapping("/login")
        @CrossOrigin(origins = "http://localhost:63342")
        public String login(@RequestParam String username, @RequestParam String password, Model model) {
            boolean isAuthenticated = userService.authenticateUser(username, password);
    
            if (isAuthenticated) {
                model.addAttribute("userName", username);
                return "redirect:/homepage?username=" + username;
            } else {
                model.addAttribute("loginError", "Login failed");
                return "login+signup";
            }
        }
    
        /**
         * Procesează cererea GET pentru afișarea paginii de autentificare și înregistrare.
         */
        @GetMapping("/login")
        @CrossOrigin(origins = "http://localhost:63342")
        public String getLogin() {
            return "login+signup";
        }
    
        /**
         * Procesează cererea POST pentru înregistrare.
         * @return Redirecționarea către pagina de autentificare sau pagina de eroare în caz de contrar(pagina ce nu exista la noi 🤷‍♂️).
         */
        @PostMapping("/signup")
        @CrossOrigin(origins = "http://localhost:63342")
        public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
            if (userService.isUsernameTaken(username)) {
                return "redirect:/signup?error=username_taken";
            }
    
            UserEntity newUser = new UserEntity(null, username, email, password, null, null, "User", null);
            userService.createUser(newUser);
    
            return "redirect:/login";
        }
    }
    ```
    
2. HomepageController
    
    ```java
    /**
     * Controller-ul HomepageController gestionează cererile HTTP legate de pagina principală.
     */
    @Controller
    public class HomepageController {
    
        /**
         * Procesează cererea GET pentru afișarea paginii principale.
         * @param model Model utilizat pentru transmiterea datelor către template.
         */
        @GetMapping("/homepage")
        public String homepage(@RequestParam("username") String userName, Model model) {
            model.addAttribute("username", userName);
            return "homepage";
        }
    ```
    
3. SongCrudController
    
    ```java
    /**
     * Controller-ul SongCrudController gestionează cererile HTTP legate de operațiile CRUD pentru cântece.
     */
    @Controller
    public class SongCrudController {
    
        /**
         * Procesează cererea GET pentru afișarea paginii de operații CRUD pentru cântece.
         * @param model Model utilizat pentru transmiterea datelor către template.
         */
        @GetMapping("/song-crud")
        public String showSongCrud(Model model) {
            return "/song-crud";
        }
    }
    ```
    

---

## Demo:

![Untitled](Documentation%20Spotynai%20f12b953dd80f4ea8a29f02a223201736/Untitled.png)

![Untitled](Documentation%20Spotynai%20f12b953dd80f4ea8a29f02a223201736/Untitled%201.png)

![Untitled](Documentation%20Spotynai%20f12b953dd80f4ea8a29f02a223201736/Untitled%202.png)

![Untitled](Documentation%20Spotynai%20f12b953dd80f4ea8a29f02a223201736/Untitled%203.png)
