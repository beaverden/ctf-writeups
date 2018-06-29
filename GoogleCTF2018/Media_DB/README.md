# Media-DB

The challenge greets us with the following important piece of code.

```
  if choice == '1':
    my_print("artist name?")
    artist = raw_input().replace('"', "")
    my_print("song name?")
    song = raw_input().replace('"', "")
    c.execute("""INSERT INTO media VALUES ("{}", "{}")""".format(artist, song))
  elif choice == '2':
    my_print("artist name?")
    artist = raw_input().replace("'", "")
    print_playlist("SELECT artist, song FROM media WHERE artist = '{}'".format(artist))
  elif choice == '3':
    my_print("song name?")
    song = raw_input().replace("'", "")
    print_playlist("SELECT artist, song FROM media WHERE song = '{}'".format(song))
  elif choice == '4':
    artist = random.choice(list(c.execute("SELECT DISTINCT artist FROM media")))[0]
    my_print("choosing songs from random artist: {}".format(artist))
    print_playlist("SELECT artist, song FROM media WHERE artist = '{}'".format(artist))
```

It is somehow obvious that is it using not-sanitized and not-escaped queries so we'll have to perform an `SQL injection`.
Well, they're not really not-sanitized, since it uses `artist = raw_input().replace("'", "")` and `song = raw_input().replace("'", "")` so we cannot use the `'` symbol to perform an sql injection. 

My first idea was to inject some SQL into the 3-rd option `song name`, but then I noticed the fact written above: it's removing the single quotes so we cannot use it in our string.

Then, the observation is that choice `1` removes `"` characters and not `'` when it inserts into the database. __How can this be helpful?__

After a careful look, we notice that it's using strings from choice `1` inside choice `4`, which prints a random artist. It uses single quotes for strings and this is exactly why it's helpful. We can pass some malicious string as an `artist name` and it will be inserted right into the query with the `format` function.

Let's use this query `name' UNION SELECT oauth_token, '1' FROM oauth_tokens WHERE '1'='1`. 
This is a classic approach, since whe know the db name and all it's tables.
First, we need to get rid of the `name = 'something` and we use `name'` so it becomes `name = 'name'`, then we can freely insert our `UNION` to get the flag value. Don't forget to get rid of the last `'` by inserting `'1'='1` which becomes `'1'='1'`.

So the select string is going to be `SELECT artist, song FROM media WHERE artist = 'name' UNION SELECT oauth_token, '1' FROM oauth_tokens WHERE '1'='1'`.
The result contains the flag `1: "1" by "CTF{fridge_cast_oauth_token_cahn4Quo}`

