# Mastery Challenge 3 (WAR)

The challenge lets us play a game.
The main idea is that the __opponent__ and the __ctfer__ both have a deck of 52 cards.
Every time a random card gets draw for both players and the one with the biggest card wins the bet. 
All the cards (values and suits) get initialized at the start, but the trick is that the __ctfer__ gets cards from 2 to 7, but the __opponent__ gets cards from 8 to 14, so we can never win fairly.

Taking a closer look at this function
```
//Reads input from user, and properly terminates the string
unsigned int readInput(char * buff, unsigned int len){
    size_t count = 0;
    char c;
    while((c = getchar()) != '\n' && c != EOF){
        if(count < (len-1)){
            buff[count] = c;
            count++;
        }
    }
	//Possible buffer overflow in here if 32 chars provided, it will overwrite the 33th char
    buff[count+1] = '\x00';
    return count;
}
```
What do you do when you read a string? 
1. You read a char
2. Put the char in the array
3. Increase the counter

This way the counter has exactly the length of the string, but it will point at a place in the string where the `NULL` terminator usually is placed in C strings.
What is wrong in this line `buff[count+1] = '\x00';`? The wrong thing is that the `NULL` terminator gets placed at length+1, so if our array is of size `20`, we can use a string of size `20` as well, and the null terminator will be placed at `arr[20]`, which doesn't exist and will overrun the buffer.

Since it's reading the name into `char name[NAMEBUFFLEN];` and it's followed by `size_t deckSize;`, `deckSize[0]` will be overwritten - which is the least significant byte on _little endian_. Exactly what we need.

If the `deckSize` becomes zero, it means we can play almost infinitely (in the humble limits if size_t, of course), because of the `gameData.deckSize--;` after the first bet. It will underflow the zero valued unsigned variable and if will become `0xFFFFFFFF`.
The implication is that `gameData.ctfer.playerCards.top++;` and `gameData.opponent.playerCards.top++;` will keep increasing forever, meaning that after finishing the arrays of cards, it will go on a rampage on the remaining memory, on the pages owned by the process. 
```
typedef struct _gameState{
  int playerMoney;
  player ctfer;
  char name[NAMEBUFFLEN];
  size_t deckSize;
  player opponent;
} gameState;
```

Notice that `ctfer` is right above `name`, which means that we could get to this array and make the game think they're real cards, but this time we control the input. 
All it takes is patience to lose 52 rounds while betting 1 coin. And then betting all-in when the win is assured.

P.S. In my version of the script, I don't rely on the `name` array, I just let the game go until I start winning and then start betting all in until I get the shell.