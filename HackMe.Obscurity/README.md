# XSS HackME

### Level 1
The first level requires you to close the value attribute value and insert a script tag with an alert. 
The code might look something like this `<input ... value='%s' ... />` so what we need to write is `something'/><script>alert(1)</script><input class='something` to fully fit with the code and make it
`<input ... value='something'/><script>alert(1)</script><input class='something' ... />`, although a `'/><script>alert(1)</script>` is enough

### Level 2
The website attempts to sanitize the input though replacing tags with their html equivalent like `&quot` or `&gt`. Many other xss vectors exists, besides `<script>`. Let's use `1' onclick='alert(1)'`.
It will run JS code when clicking on div