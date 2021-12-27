#include "filaSequencial.h"

#include <stdio.h>
#include <stdlib.h>

int ScreateQueue(){
	Sstart = -1;
	Send = -1;
	return 1;
}

int SdestroyQueue(){
	Sstart = -1;
	Send = -1;
	return 1;
}

int SisQueueEmpty(){
	return (Send == -1) ? 1 : 0;
}

int SisQueueFull(){
	return (Send == max_tam_queue-1) ? 1 : 0;
}

int SgetQueueSize(){
	return Send+1;
}

int Senqueue(int value){
	if(SisQueueFull()){
		return 0;
	}

	Squeue[++Send] = value;
	Sstart = 0;
	return 1;
}

int Sdequeue(){
	int i;
	if(SisQueueEmpty()){
		return 0;
	}
	printf("\nValor removido: %d\n", Speek());
	for(i=0; i<Send; i++){
		Squeue[i] = Squeue[i+1];
		if(i == max_tam_queue-1)
			break;
	}
	Send--;
	return 1;
}

int Speek(){
	if(SisQueueEmpty())
		return -999;
	return Squeue[Sstart];
}

void SprintQueue(){
	int i;
	for(i=0; i<SgetQueueSize(); i++){
		printf("%d-", Squeue[i]);
	}
	printf("\n");
}
