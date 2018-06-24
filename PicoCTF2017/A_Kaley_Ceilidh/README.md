# A Kaley Ceilidh

The challenge hints us that it's using a `Humongous` database, which, as usual, is a homonym for "Mongoose", hence MongoDB.
The thing with NoSQL databases is that you can inject stuff too, if the programmer is not careful. And even when he is, it's much harder to escape the sequence like in SQL languages, especially when the _database_ itself provides you with the ability to execute arbitrary code inside a `$where` operator.

So here it looks like the client puts up a json object he passes to the server and it directly gets tossed into some `find` function of the database.
If there are no checks and no processing, we might be able to pass something interesting to the database to execute.

Obviously, since we want to pass a `$where` clause, the site's interface isn't of any help, because it uses the keys `name`, `cost`, `tag`, etc.

I started [Postman](https://www.getpostman.com/) and sent a few `POST` requests to `http://shell2017.picoctf.com:8080/search`, with the given json payload `{ "$where" : "function() {}"  }`. 
The results came 

```
{
    "data": [],
    "time": 2
}
```

1. The `data` array contains all the items that matched the search criteria.
2. The `time` field looks like the execution time, nothing special.

But even though we may pass arbitrary code to `$where`, how can we manipulate the `data` array, since the value we're passing is basically a compare function?
We don't know the database name, the collection name - nothing. It means we can't update or insert anything, so no data manipulation. All we know is that there might be a `flag` property in some of the entries (_second hint_)

There still is a thing we can manipulate pretty easily - `time` field. With the help of `sleep` function we may put the system to sleep for as long as we need. But how how long? As long as the first character of the flag, the as long as the second character and so on till we get our flag. By the resulting `time` we can guess what the character actually is.
 
So, our second version of the payload 
```
"$where" : "function() { if (Object.prototype.toString.call(this.flag) == '[object String]') sleep(this.flag.charCodeAt(%d)*20); else sleep(1);  }" % (i)
```
Forces the system to sleep for a defined amount of time for each character, multiplied by 20 to eliminate some of the noise.
After that, we just divide the time by 20 and try 4 more times and select the most frequent character we got. 

The flag is `flag{I_only_eat_0rg4n1c_flages}`