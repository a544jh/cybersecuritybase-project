### A3: XSS, JavaScript that can access session cookie can be injected
Add a new todo with content `<script>alert(document.cookie)</script>`

To fix:
Change `th:utext` to `th:text` in `todos.html`
Remove `context.setUseHttpOnly(false)` from `MyEmbeddedServletContainerCustomizer.java`

### A2: Trivial to guess session tokens.
You can hijack someone's session just by guessing a session id, 
since the embedded server's session id generator has been replaced by a very weak one.

For example you can use the javascript console to set the cookie: `document.cookie = "JSESSIONID=3"`

To fix:
Remove the custom session id generator from `MyEmbeddedServletContainerCustomizer.java`

