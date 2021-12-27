/*
 ============================================================================
 Name        : Main.c
 Author      : Andrey H. L. Zeferino | Vitor Araujo Lins
 Version     : 0.1
 Description : Trabalho de estrutura de dados, implementação fila e pilha sequenciais e encadeadas
 ============================================================================
*/

#include <stdio.h>
#include <stdlib.h>
#include "Pilha/pilhaEncadeada.h"
#include "Pilha/pilhaSequencial.h"
#include "Fila/filaEncadeada.h"
#include "Fila/filaSequencial.h"


void menuPilhaSequencial(){
	int opcaoSeguinte;
	int valor;
	do {
        printf("\n1 - Empilhar (Push) \n");
        printf("2 - Desempilhar (Pop) \n");
        printf("3 - Busca elemento do topo \n");
        printf("4 - Tamanho da Pilha \n");
        printf("5 - Imprime Pilha \n");
        printf("6 - Sair\n");

        scanf("%d", &opcaoSeguinte);


            switch(opcaoSeguinte) {
                case 1:
                	printf("\nDigite um valor: ");
                	scanf("%d",&valor);
                    Spush(valor);
                break;

                case 2:

                    Spop();
                break;

                case 3:
        		 valor = Stop();
        		 if(valor != -999){
        			 printf("\n%d\n", valor);
        		 }
                break;

                case 4:
                    printf("\n%d\n", SgetStackSize());
                break;

                case 5:
                    SprintStack();
                break;

                case 6:
                    break;

                }
	}while(opcaoSeguinte != 6);
}

void menuPilhaEncadeada(){
	int opcaoSeguinte;
	int valor;
	do {
         printf("\n1 - Empilhar (Push) \n");
         printf("2 - Desempilhar (Pop) \n");
         printf("3 - Busca elemento do topo \n");
         printf("4 - Tamanho da Pilha \n");
         printf("5 - Imprime Pilha \n");
         printf("6 - Sair\n");

         scanf("%d", &opcaoSeguinte);


             switch(opcaoSeguinte) {
                 case 1:
                	 printf("\nDigite um valor: ");
                	 scanf("%d",&valor);
                	 Epush(valor);
                 break;

                 case 2:
                     Epop();
                 break;

                 case 3:
    			  valor = Etop();
    			  if(valor != -999){
    				  printf("\n%d\n", valor);
    			  }
                 break;

                 case 4:
                	 printf("\n%d\n", EgetStackSize());
                 break;

                 case 5:
                     EprintStack();
                 break;

                 case 6:
                     break;

                 }
  }while(opcaoSeguinte != 6);

}

  void menuFilaSequencial(){
	  int opcaoSeguinte;
	  int valor;
	  do {
          printf("\n1 - Enfileirar (enqueue) \n");
          printf("2 - Desenfileirar (dequeue) \n");
          printf("3 - Busca elemento do inicio \n");
          printf("4 - Tamanho da fila \n");
          printf("5 - Imprime fila \n");
          printf("6 - Sair\n");

          scanf("%d", &opcaoSeguinte);


              switch(opcaoSeguinte) {
				  case 1:
					 printf("\nDigite um valor: ");
					 scanf("%d",&valor);
					 Senqueue(valor);
				  break;

				  case 2:
					  Sdequeue();
				  break;

				  case 3:
				   	 valor = Speek();
				   	 if(valor != -999){
				   		printf("\n%d\n", valor);
				   	 }
				  break;

				  case 4:
					 printf("\n%d\n", SgetQueueSize());
				  break;

				  case 5:
					  SprintQueue();
				  break;

				  case 6:
					  break;

                  }
   }while(opcaoSeguinte != 6);

}

   void menuFilaEncadeada(){
	   int opcaoSeguinte;
	   int valor;
	   do {
		   printf("\n1 - Enfileirar (enqueue) \n");
		   printf("2 - Desenfileirar (dequeue) \n");
		   printf("3 - Busca elemento do inicio \n");
		   printf("4 - Tamanho da fila \n");
		   printf("5 - Imprime fila \n");
		   printf("6 - Sair\n");

		   scanf("%d", &opcaoSeguinte);


		   switch(opcaoSeguinte) {
		   	   case 1:
		   		printf("\nDigite um valor: ");
		   		scanf("%d",&valor);
		   		Eenqueue(valor);
		   		break;

		   		case 2:
		   		 Edequeue();
		   		 break;

		   		case 3:
		   		 valor = Epeek();
		   		 if(valor != -999){
		   			printf("\n%d\n", valor);
		   		 }

		   		 break;

		   		case 4:
		   		 printf("\n%d\n", EgetQueueSize());
		   		 break;

		   		case 5:
		   		 EprintQueue();
		   		 break;

		   		case 6:
		   		 break;

		   }
	   }while(opcaoSeguinte != 6);

}

int main() {
	int opcao;
	ScreateQueue();
	EcreateQueue();
	ScreateStack();
	EcreateStack();
    do {

            printf("\nEscolha sua opcao: \n");
            printf("1 - PILHA sequencial \n");
            printf("2 - PILHA encadeada \n");
            printf("3 - FILA sequencial \n");
            printf("4 - FILA encadeada \n");
            printf("5 - Sair\n");

            scanf("%d", &opcao);

                switch(opcao) {

                    case 1:
                        menuPilhaSequencial();
                    break;

                    case 2:
                        menuPilhaEncadeada();
                    break;

                    case 3:
                        menuFilaSequencial();
                    break;

                    case 4:
                        menuFilaEncadeada();
                    break;

                }
    }while(opcao != 5);
    SdestroyQueue();
    EdestroyQueue();
    SdestroyQueue();
    EdestroyStack();
    return 0;
}
