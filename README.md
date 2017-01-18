# Cyber Security Base - Course Project I
Java Spring application with added vulnerabilities.

## How to run
Open project in NetBeans and run it from there.

## The vulnerabilities

### A3 - Cross-Site Scripting (XSS): JavaScript that can access session cookie can be injected
1. Open http://localhost:8080
2. Log in with username and password "ted"
3. Add a new todo with content `<script>alert(document.cookie)</script>`
This could be replaced with malicious code.

#### To fix:
Change `th:utext` to `th:text` in `todos.html`
Remove `context.setUseHttpOnly(false)` from `MyEmbeddedServletContainerCustomizer.java`

### A2 - Broken Authentication and Session Management: Trivial to guess session tokens.
You can hijack someone's session just by guessing a session id, 
since the embedded server's session id generator has been replaced by a very weak one.

1. Open http://localhost:8080
2. Log in with username and password "ted"
3. Open your browser's developer tools and check the JSESSIONID
3. Open the same page in an incognito window
4. Use the javascript console to set the cookie: `document.cookie = "JSESSIONID=2"` and refresh the page to hijack the other session.

#### To fix:
Remove the custom session id generator from `MyEmbeddedServletContainerCustomizer.java`

### A7 - Missing Function Level Access Control: No ACL check for todo deletion
Anyone can delete anyone's todo by just sending a GET to `todos/{id}/delete`


1. Open http://localhost:8080
2. Log in with username and password "ted"
3. Make a few new todos and copy one of the Delete link urls
4. Click Logout
5. Paste the url into the address bar
6. Log back in and the todo should have been deleted

#### To fix:
Add account check to the `deleteTodo()` method in `TodoController.java`

### A8 - Cross-Site Request Forgery (CSRF): Todo deletion CSRF
For example one can add a todo with the text (or even in eg. an e-mail): `<img src="todos/3/delete></img>`
to make the browser send a delete request when the page is viewed.

1. Open http://localhost:8080
2. Log in with username and password "ted"
3. Add a few todos
4. Add todo with text `<img src="todos/1/delete></img>`
5. The first one should be deleted

#### To fix:
Change the request method of deleteTodo() to POST in `TodoController.java`
Make corresponding form in `todos.html`
Change `th:utext` to `th:text` in `todos.html`
Remove `http.csrf.disable()` in `SecurityConfiguration.java`

### A6 - Sensitive Data Exposure: Passwords stored in plaintext
If the database would be compromised, the passwords would be readily available.
The test user's password was previously hashed in the source code, but now it's in plaintext.

#### To fix:
Change `new PlaintextPasswordEncoder()` to `new BCryptPasswordEncoder()` in `SecurityConfiguration.java`
(the imports need to be changed as well).
