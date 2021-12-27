#include "pilhaSequencial.h"

#include <stdlib.h>
#include <stdio.h>

int isStackFull(){
	return (STopo == max_tam_stack) ? 1 : 0;
}

void ScreateStack(){
	STopo = -1;
}

int Spush(int valor){
	STopo++;
	if(!isStackFull()){
		SPilha[STopo] = valor;
		return 1;
	}
	return 0;
}

int SisStackEmpty(){
	return (STopo == -1) ? 1 : 0;
}

int Stop(){
	if(SisStackEmpty())
		return -999;
	return SPilha[STopo];

}

int SgetStackSize(){
	return STopo+1;
}

void SprintStack(){
	if(!SisStackEmpty()){
		int i;
		for(i=STopo; i>-1; i--){
			printf("\n%d", SPilha[i]);
		}
	}
}
int Spop(){
	if(!SisStackEmpty()){
		printf("\nElemento removido: %d", Stop());
		STopo--;
		return 1;
	}
	return 0;
}

int SdestroyStack(){
	if(!SisStackEmpty()){
		STopo = -1;
		return 1;
	}
	return 0;

}

