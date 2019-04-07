# got-shell?

If we examine the sections of the executable, we have only a few options where we can write, but the most important is `.got.plt` the Global Offset Table

We just pick the address of the `puts` function in there and overwrite it with the address of the `win` function. Now, on the next call to `puts`, our function will be called.

We got the shell, execute `cat flag.txt` and get `picoCTF{m4sT3r_0f_tH3_g0t_t4b1e_d3c1afdd}`