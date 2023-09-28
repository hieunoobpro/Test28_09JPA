<h2>1. Thuộc tính name trong annotation @Entity khác với thuộc tính name trong @Table như thế nào? Hãy giải thích rõ cần thì minh hoạ</h2>
<p>Trả lời:</p>
   <p>@Entity được sử dụng để đánh dấu một lớp Java là một đối tượng có thể được lưu trữ trong cơ sở dữ liệu. Nó thông báo cho Hibernate rằng lớp này cần được ánh xạ thành một bảng trong cơ sở dữ liệu</p>
Trong annotation @Entity, thuộc tính name được sử dụng để chỉ định tên của bảng trong cơ sở dữ liệu mà lớp đối tượng sẽ được ánh xạ. Mặc định, Hibernate sẽ sử dụng tên của lớp đối tượng làm tên của bảng. 
<p>Ví dụ: 
<p>@Entity
<p>public class Employee {
<p> @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;</p>
   <p> private String name;
    <p>private Integer age;
<p>private String address;
}</p>
<p>Annotation @Table cũng được sử dụng để ánh xạ một lớp thành một bảng trong cơ sở dữ liệu. Tuy nhiên, khác với @Entity chỉ định trực tiếp trên lớp, @Table được sử dụng để chỉ định tên bảng cho một lớp đã được đánh dấu bằng @Entity. Ví dụ:</p>
<p>@Entity
<p>@Table(name = "employees")
<p>public class Employee {
<p> @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;</p>
    <p> private String name;
    <p>private Integer age;
<p>private String address;
}</p>
<p>Trong trường hợp này, lớp Employee cũng được ánh xạ thành bảng có tên là "employees" trong cơ sở dữ liệu.</p>
<h2>2. Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file application.properties? </h2 >
<p>Để debug câu lệnh SQL mà Hibernate sinh ra trong quá trình thực thi, bạn cần bổ sung các cấu hình sau vào file application.properties như sau:</p>
<p>spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE</p>
<p>- spring.jpa.show-sql=true sẽ hiển thị câu lệnh SQL được sinh ra bởi Hibernate trong quá trình thực thi.
<p>- spring.jpa.properties.hibernate.format_sql=true sẽ định dạng câu lệnh SQL để chúng dễ đọc hơn.</p>
<p>- Cấu hình logging.level.org.hibernate.SQL=DEBUG sẽ kích hoạt ghi log ở mức DEBUG cho câu lệnh SQL được sinh ra bởi Hibernate.</p>
<p>- Cấu hình logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE sẽ kích hoạt ghi log ở mức TRACE cho các tham số của câu lệnh SQL.</p>
<p>Sau khi bổ sung các cấu hình này vào file application.properties, Hibernate sẽ ghi log các câu lệnh SQL và các tham số tương ứng vào cơ sở dữ liệu mỗi khi chúng được thực thi. Ta có thể kiểm tra log trong ứng dụng hoặc trong các công cụ quản lý log để xem các câu lệnh SQL được sinh ra và tham số của chúng.</p>
//Tham khảo file application.properties
<h2>3. Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính.</h2>

<h4>- Tham số nào trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính
<h4>- Tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu
<h4>- Tham số nào buộc trường không được null?</h4>

<p>a, Tham số name: Tham số này được sử dụng để đổi tên cột nếu muốn khác với tên thuộc tính. Mặc định, Hibernate sẽ sử dụng tên thuộc tính làm tên cột. Ví dụ:
<p>@Column(name = "full_name")
<p>private String fullName;
<p>Trong ví dụ này, cột tương ứng với thuộc tính fullName sẽ có tên là "full_name" trong cơ sở dữ liệu.</p>
<p></p>
<p>b, Tham số unique: Tham số này được sử dụng để chỉ định yêu cầu duy nhất (unique) cho cột. Nếu bạn đặt giá trị unique = true, Hibernate sẽ tạo ràng buộc duy nhất trên cột tương ứng trong cơ sở dữ liệu. Điều này đảm bảo rằng không có giá trị trùng lặp trong cột đó. Ví dụ:</p>
<p>@Column(unique = true)</p>
<p>private String email;</p>
<p>Trong ví dụ này, cột email sẽ có ràng buộc duy nhất, đảm bảo không có hai bản ghi nào trong cơ sở dữ liệu có cùng giá trị email.</p>
<p>c, Tham số nullable: Tham số này được sử dụng để buộc trường không được null (không có giá trị null). Mặc định, giá trị nullable là true, cho phép trường có giá trị null. Nếu bạn đặt giá trị nullable = false, Hibernate sẽ tạo ràng buộc để đảm bảo trường tương ứng không được null. Ví dụ:</p>
<p>@Column(nullable = false)</p>
<p>    private String phone;</p>
Trong ví dụ này, trường phone sẽ không được null, và Hibernate sẽ tạo ràng buộc để đảm bảo rằng không có giá trị null được chấp nhận cho trường này trong cơ sở dữ liệu.
<p>//Chi tiết trong Test 3</p>
<h2>4. Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung</h2>
<h4>- Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)</h4>
<h4>- Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
Vậy 2 annotation này là gì</h4>
<p> Để bắt được hai sự kiện trước khi đối tượng Entity lưu hoặc cập nhật xuống CSDL, bạn có thể sử dụng hai annotation sau:</p>
<p>- @PrePersist: Annotation này được đặt trên một phương thức trong Entity class và được sử dụng để xác định logic sẽ được thực thi ngay trước khi đối tượng Entity được lưu (trước lệnh INSERT). Phương thức được chú thích bởi @PrePersist sẽ được gọi tự động trước khi Hibernate thực hiện lệnh INSERT để lưu đối tượng vào CSDL. </p>
<p>- @PreUpdate: Annotation này cũng được đặt trên một phương thức trong Entity class và được sử dụng để xác định logic sẽ được thực thi ngay trước khi đối tượng Entity được cập nhật (trước lệnh UPDATE). Phương thức được chú thích bởi @PreUpdate sẽ được gọi tự động trước khi Hibernate thực hiện lệnh UPDATE để cập nhật đối tượng trong CSDL</p>
<p>   Khi một đối tượng Employee được lưu (INSERT) hoặc cập nhật (UPDATE) trong CSDL, phương thức được chú thích tương ứng (beforePersist() hoặc beforeUpdate()) sẽ được gọi tự động để thực thi logic bổ sung trước khi thực hiện lệnh INSERT hoặc UPDATE.</p>
<h2>5. JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?</h2>

JpaRepository là một interface có sẵn trong thư viện JPA, nó cung cấp các mẫu hàm thuận tiện cho thao tác dữ liệu.

### Kế thừa

JpaRepository kế thừa từ interface CrudRepository<T, ID extends Serializable>. CrudRepository cung cấp các phương thức cơ bản cho thao tác dữ liệu như thêm, sửa, xóa và tìm kiếm. JpaRepository kế thừa từ CrudRepository và thêm các phương thức mở rộng cho các thao tác phức tạp hơn như phân trang, sắp xếp và truy vấn dựa trên các biểu thức JPQL.

### Các phương thức

JpaRepository<T, ID extends Serializable> có các phương thức sau:

* `save(T entity)`: Thêm một bản ghi mới vào cơ sở dữ liệu.
* `findById(ID id)`: Tìm một bản ghi theo ID.
* `findAll()`: Tìm tất cả các bản ghi.
* `delete(T entity)`: Xóa một bản ghi.

JpaRepository cũng cung cấp các phương thức mở rộng như:

* `findById(ID id, Sort sort)`: Tìm một bản ghi theo ID và sắp xếp.
* `findBy(Specification<T> spec)`: Tìm các bản ghi dựa trên một biểu thức JPQL.
* `findBy(String propertyName, Object value)`: Tìm các bản ghi dựa trên một thuộc tính và giá trị.

### Sử dụng

Để sử dụng JpaRepository, bạn cần khai báo một interface kế thừa từ JpaRepository và chỉ định kiểu dữ liệu của bản ghi và kiểu dữ liệu của khóa chính. Ví dụ:

java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}


Interface này sẽ cung cấp các phương thức thao tác dữ liệu cho đối tượng `User`.

## 6. Hãy viết khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là Long, tuân thủ interface JpaRepository.
## Câu trả lời
### Khai báo interface repository cho Entity Post

Để khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là Long, tuân thủ interface JpaRepository, ta có thể sử dụng cú pháp sau:

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}


**Giải thích:**

* `@Repository` là một annotation được sử dụng để đánh dấu một interface là một repository.
* `JpaRepository` là một interface có sẵn trong thư viện JPA, cung cấp các phương thức thao tác dữ liệu cơ bản.
* `Post` là tên của Entity mà repository sẽ thao tác.
* `Long` là kiểu dữ liệu của trường Identity.

**Ví dụ:**

java
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

}

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}


Interface `PostRepository` sẽ cung cấp các phương thức sau:

* `save(Post post)`: Thêm một bản ghi mới vào cơ sở dữ liệu.
* `findById(Long id)`: Tìm một bản ghi theo ID.
* `findAll()`: Tìm tất cả các bản ghi.
* `delete(Post post)`: Xóa một bản ghi.

**Thêm các phương thức truy vấn tùy chỉnh:**

Ngoài ra, ta có thể thêm các phương thức truy vấn tùy chỉnh vào interface `PostRepository`. Ví dụ:

java
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitle(String title);

    List<Post> findByContent(String content);

}


Các phương thức truy vấn này sẽ được Spring Data JPA tự động triển khai dựa trên các câu lệnh JPQL.
#### //Xem Test6
##7. Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì có cần phải dùng xác định unique dùng annotation @Column(unique=true) không?
## Câu trả lời

Không cần thiết phải sử dụng annotation `@Column(unique=true)` khi đã chọn một cột là Identity dùng `@Id` để đánh dấu. Bởi vì, các cột Identity luôn được tạo ràng buộc duy nhất (unique constraint) trong cơ sở dữ liệu.

### Giải thích

Ràng buộc duy nhất (unique constraint) là một ràng buộc ràng buộc các giá trị trong một cột phải duy nhất. Khi một cột được đánh dấu là Identity, Hibernate sẽ tự động tạo ràng buộc duy nhất cho cột đó trong cơ sở dữ liệu.

Ví dụ:

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

}


Trong ví dụ trên, cột `id` được đánh dấu là Identity. Do đó, ràng buộc duy nhất sẽ được tự động tạo cho cột này trong cơ sở dữ liệu. Điều này có nghĩa là không thể có hai bản ghi trong bảng `posts` có giá trị `id` giống nhau.

Tuy nhiên, bạn vẫn có thể sử dụng annotation `@Column(unique=true)` để chỉ định ràng buộc duy nhất cho cột Identity. Điều này có thể hữu ích nếu bạn muốn rõ ràng về ý định của mình.

Ví dụ:

java
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String title;

    private String content;

}


Trong ví dụ này, ràng buộc duy nhất cho cột `id` được chỉ định rõ ràng bằng cách sử dụng annotation `@Column(unique = true)`.

### Kết luận

Có thể sử dụng hoặc không sử dụng annotation `@Column(unique=true)` với cột Identity. Nếu bạn không sử dụng annotation này, Hibernate sẽ tự động tạo ràng buộc duy nhất cho cột trong cơ sở dữ liệu.

## 8. Giả sử có 1 class Employee với các fields sau {id, emailAddress, firstName, lastName}. Hãy viết các method trong interface EmployeeRespository để :
* Tìm tất cả các Employee theo emailAddress và lastName
* Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
* Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
* Tìm tất cả các Employee theo fistName không phân biệt hoa thường

## Trả lời

Dưới đây là các method trong interface EmployeeRepository để thực hiện các yêu cầu như bạn đã đề cập:

### Tìm tất cả các Employee theo emailAddress và lastName
    /**
     * Tìm tất cả các Employee theo emailAddress và lastName
     *
     * @param emailAddress Email của Employee
     * @param lastName Họ của Employee
     * @return List<Employee>
     */
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);


Phương thức này sử dụng câu lệnh JPQL `SELECT e FROM Employee e WHERE e.emailAddress = :emailAddress AND e.lastName = :lastName` để tìm tất cả các Employee có emailAddress và lastName khớp với các giá trị được truyền vào.

### Tìm tất cả các Employee khác nhau theo firstName hoặc lastName

   
     * Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
     *
     * @return List<Employee>
     */
    List<Employee> findDistinctByFirstNameOrLastName();


Phương thức này sử dụng câu lệnh JPQL `SELECT DISTINCT e FROM Employee e WHERE e.firstName = :firstName OR e.lastName = :lastName` để tìm tất cả các Employee khác nhau có firstName hoặc lastName khớp với các giá trị được truyền vào.

### Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần


 
     * Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
     *
     * @return List<Employee>
     */
    List<Employee> findByLastNameOrderByFirstNameAsc();


Phương thức này sử dụng câu lệnh JPQL `SELECT e FROM Employee e ORDER BY e.lastName, e.firstName ASC` để tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần.

### Tìm tất cả các Employee theo fistName không phân biệt hoa thường



     * Tìm tất cả các Employee theo fistName không phân biệt hoa thường
     *
     * @param firstName Tên của Employee
     * @return List<Employee>
     */
    List<Employee> findByFirstNameIgnoreCase(String firstName);


Phương thức này sử dụng câu lệnh JPQL `SELECT e FROM Employee e WHERE LOWER(e.firstName) = LOWER(:firstName)` để tìm tất cả các Employee có firstName khớp với giá trị được truyền vào, không phân biệt hoa thường.

### Kết luận

Các phương thức này có thể được sử dụng trong ứng dụng của bạn để truy vấn dữ liệu từ bảng `employees`.
#### Tham khảo Test8
## 9. Hãy nêu cách sử dụng của @NamedQuery và @Query. Cho ví dụ
## Trả lời
Cả hai annotation @NamedQuery và @Query trong JPA được sử dụng để định nghĩa truy vấn tùy chỉnh để truy xuất dữ liệu từ cơ sở dữ liệu. Tuy nhiên, chúng có cách sử dụng và cú pháp khác nhau.
###@NamedQuery:

* @NamedQuery được sử dụng để định nghĩa truy vấn tùy chỉnh trong phần khai báo của Entity class.

* Truy vấn được đặt tên và có thể được gọi bằng tên đó trong mã Java để thực thi truy vấn.

### Cú pháp:

     * @Entity
     *
     * @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
     * public class Employee {
     ...
    }
Trong ví dụ trên, truy vấn có tên là "Employee.findByLastName" và nó sẽ trả về danh sách các đối tượng Employee có lastName giống với giá trị truyền vào.

Để thực thi truy vấn @NamedQuery, ta có thể sử dụng EntityManager như sau:

* TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByLastName", Employee.class);
query.setParameter("lastName", "Smith");
List<Employee> employees = query.getResultList();
  
### @Query

* @Query được sử dụng để định nghĩa truy vấn tùy chỉnh trực tiếp trong phương thức của Repository interface.

* Truy vấn được viết bằng cú pháp JPQL (Java Persistence Query Language) hoặc Native SQL.

### Cú pháp sử dụng @Query như sau:
    * @Repository
      public interface EmployeeRepository extends JpaRepository<Employee, Long> {
      @Query("SELECT e FROM Employee e WHERE e.lastName = :lastName")
      List<Employee> findByLastName(@Param("lastName") String lastName);
      @Query(value = "SELECT * FROM employees WHERE last_name = :lastName", nativeQuery = true)
      List<Employee> findByLastNameNative(@Param("lastName") String lastName);
     }
* Trong ví dụ trên, chúng ta có hai phương thức tìm kiếm findByLastName. Phương thức đầu tiên sử dụng JPQL để truy vấn, trong khi phương thức thứ hai sử dụng Native SQL.
  
 Để thực thi truy vấn @Query, chúng ta có thể gọi trực tiếp phương thức trong Repository interface:
              
     List<Employee> employees = employeeRepository.findByLastName("Smith");
//Tham khảo Test9
##9. Hãy nêu 1 ví dụ sử dụng sorting và paging khi query đối tượng Employee ở trên
## Trả lời 
Để sử dụng sorting và paging khi truy vấn đối tượng Employee, bạn có thể sử dụng Sort và Pageable trong Spring Data JPA. Dưới đây là một ví dụ:
       
        @Repository
        public interface EmployeeRepository extends JpaRepository<Employee, Long> {
        // Truy vấn danh sách Employee với sắp xếp và phân trang
        Page<Employee> findAll(Pageable pageable);
        }
Trong ví dụ trên, phương thức findAll trong EmployeeRepository sử dụng Pageable để thực hiện sorting và paging. Pageable cho phép bạn chỉ định thông tin về số trang, kích thước trang, và cách sắp xếp kết quả truy vấn.

Để sử dụng phương thức này, bạn có thể cung cấp một đối tượng Pageable trong mã Java để lấy danh sách Employee được sắp xếp và phân trang:
@Service
public class EmployeeService {
private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Page<Employee> getEmployeesSortedAndPaged(int page, int size, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }
Trong ví dụ trên, EmployeeService sử dụng EmployeeRepository để truy vấn danh sách Employee được sắp xếp và phân trang. Phương thức getEmployeesSortedAndPaged nhận đầu vào số trang, kích thước trang và trường sắp xếp (sortBy) và trả về một trang (Page) chứa kết quả truy vấn.
* Tham khảo Test10
##11. Có 3 Entity Product.java và Category.java và Tag.java
* Hãy bổ sung định nghĩa quan hệ One to Many giữa bảng Category (One) – Product (Many). Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.
* Hãy bổ sung định nghĩa quan hệ Many to Many giữa bảng Tag(Many) – Product(Many).
## Trả lời
Để bổ sung định nghĩa quan hệ Many-to-Many giữa bảng Tag (Many) và Product (Many), chúng ta cần tạo một bảng liên kết (junction table) để lưu trữ quan hệ này. Ta có:

    @Entity
    public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToMany
    @JoinTable(
    name = "product_tag",
    joinColumns = @JoinColumn(name = "tag_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    }
và


    @Entity
    public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToMany(mappedBy = "products")
    private List<Tag> tags;
    
    }
Để bổ sung định nghĩa quan hệ One-to-Many giữa bảng Category (One) và Product (Many) trong JPA, bạn có thể sử dụng các annotation @OneToMany và @ManyToOne. Đồng thời, để đảm bảo rằng khi một Category bị xóa, các Product không bị xóa mà chỉ được cập nhật thuộc tính Category là null, bạn có thể sử dụng annotation @JoinColumn và thiết lập CascadeType.


    @Entity
    public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
    
    }
và


    @Entity
    public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    }

* Xem Test11

##12. Viết câu lệnh query để tìm kiếm UserDto bao gồm các thuộc tính (id, name, email) theo cách sau (mỗi cách 1 câu lệnh truy vấn)
* Method query
* JPQL Query
* Native Query
* Projection
## Trả lời
Method query

    List<UserDto> findAllBy();
JPQL Query:

    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDto> findAllWithDto();
Native Query:

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT id, name, email FROM user", nativeQuery = true)
    List<Object[]> findAllWithObjectArray();
    }
Projection:

    List<UserProjection> findAllBy();

Trong đó, UserProjection là một interface projection được định nghĩa như sau:

    public interface UserProjection {
    Long getId();
    String getName();
    String getEmail();
    }
Interface projection cho phép bạn chỉ định các phương thức getter tương ứng với các thuộc tính mà bạn muốn truy vấn trả về.
##13. Viết custom generate id để tạo id ngẫu nhiên cho đối tượng post ở trên
* ! Chú ý custom generate id ở đây là tự động tạo ID giống như @GeneratedValue, chúng ta không cần tự set ID thủ công cho Entity mà ID sẽ được tự động thêm vào
  
## Trả Lời

Ví dụ sử dụng thời gian và ngẫu nhiên để tạo ID cho đối tượng Post


    @Entity
    public class Post {
    @Id
    private String id;
    private String title;
    
    @PrePersist
    private void generateId() {
    if (id == null) {
    // Tạo ID từ thời gian và số ngẫu nhiên
    String timestamp = LocalDateTime.now().toString();
    String random = String.valueOf(new Random().nextInt(1000));
    id = timestamp + random;
    }
    }
Trong ví dụ trên, chúng ta đã sử dụng annotation @PrePersist và phương thức generateId() tương tự như trước. Tuy nhiên, thay vì sử dụng UUID, chúng ta sử dụng thời gian hiện tại (LocalDateTime.now()) và một số ngẫu nhiên (new Random().nextInt(1000)) để tạo ID. ID được tạo bằng cách kết hợp chuỗi thời gian và số ngẫu nhiên.
  




   

