# XSS HackME

### Level 1
The first level requires you to close the value attribute value and insert a script tag with an alert. 
The code might look something like this `<input ... value='%s' ... />` so what we need to write is `something'/><script>alert(1)</script><input class='something` to fully fit with the code and make it
`<input ... value='something'/><script>alert(1)</script><input class='something' ... />`, although a `'/><script>alert(1)</script>` is enough

### Level 2
The website attempts to sanitize the input though replacing tags with their html equivalent like `&quot` or `&gt`. Many other xss vectors exists, besides `<script>`. Let's use `1' onclick='alert(1)'`.
It will run JS code when clicking on div

### Level 3
The third level gets the `text` from the `GET` parameter `xss`, attempts to sanitize it and then creates a `<li ${text} >Hello World</li>`. It is clear that we can't use `<>'/"` but we are free to use `onclick` again : )
`https://hackme.obscurity.app/dashboard/?page=xss&level=3&&xss=onclick=alert(1)` will spawn the alert box

### Level 4

The `JS` file on the page is written in `JSFuck`, javascript obfuscator that turns code into a turing complete sublanguage containing only a few symbols. 
We can use [Decoder-JSFuck](https://enkhee-osiris.github.io/Decoder-JSFuck/) to get the real code.

```
$(document).ready(function() {                 
    if(sessionStorage.getItem('xss')) {             
        ("#card-xss").append(sessionStorage.getItem('xss'));         
    }     
});
```

It is now clear that our xss vector is `sessionStorage` so we can just add an item in the console like that `sessionStorage.setItem("xss", "<script>alert(1)</script>");` and the refresh the page. 
SessionStorage is persistent across sessions, so the value will remain even after refresh.

### Level 5
This level does sanitization on inputs, but the ``` let input = `...` ``` is vulnerable, so we can close the backtick, add some valid js code and the open the backtick, to match the pattern.
Something like ``` let input = `1` + alert(1) + `1` ``` and the data we need to send is ``` 1` + alert(1) + `1  ```