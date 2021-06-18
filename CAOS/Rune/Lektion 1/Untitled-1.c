#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "common.h"
#include "common_threads.h"

const int NoThreads=3;
long arr[9]={1,2,3, 4,5,6, 7,8,9};
long sum[3]={0,0,0};

//Mutex og condition erklaeringer
void *mythread(void *arg, int *lock) {
Pthread_lock_t lock;
sem_t m;  
long id=(long)arg;
printf("Traad %ld: start\n", id);
Pthread_mutex_lock(&lock);
long total=0;
for(int i=0;i<NoThreads;i++)
total+= arr[id*NoThreads+i];
sum[id]=total;
Pthread_mutex_unlock(&lock);
printf("Traad %ld: foer: %ld\n", id, total);

//22: Traaden skal vente her paa de andre traade
sem_init(&m,0,3);
sem_wait(&m);
total=0;
for(int i=0;i<NoThreads;i++) total+=sum[i];
printf("Traad %ld: efter: %ld\n", id, total);
sem_post(&m);
//fortsat beregning; ikke en del af opgaven.
return NULL;
}

int main(int argc, char *argv[]) {
pthread_t t1, t2,t3,t4;
Pthread_create(&t1, NULL, mythread, (void*)0);
Pthread_create(&t2, NULL, mythread, (void*)1);
Pthread_create(&t3, NULL, mythread, (void*)2);
Pthread_join(t1, NULL);
Pthread_join(t2, NULL);
Pthread_join(t3, NULL);
return 0;
}

void calc3x3(vec_t *v, const int Length, Long* calcResult, Long* calcResult2, Long* calcResult3) {
    int i;
    *calcResult = 0;
    *calcResult2 = 0;
    *calcResult3 = 0;

    ∗ calcResult+=v[i] . x∗v [ i ] . x + v [ i ] . y∗v [ i ] . y ;

    for (i = 0; i < Length; i++) {
      *calcResult = x*v[i] 
      *calcResult2 = y * v[i]
      *calcResult3 +=v[i]
    }
    for(;i<size;i++) 
	//Loop til at færdiggøre elementer
}