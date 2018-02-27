#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h> 
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#define MAXSIZE 50 
char ex[]="stop";
char ex1[]="STOP";
char ex2[]="Stop";

struct studs {
char regno[10];
char name[50];
char add[50];
char dept[10];
char course[10];
char sec[2], sem[2];
char sub[10];
char marks[10];
};

main()
{ 
	struct studs s[3];
	strcpy( s[0].regno, "1234");
	strcpy( s[0].name, "XYZ");
	strcpy( s[0].add, "Block 15, MIT");
	strcpy( s[0].dept, "ICT");
	strcpy( s[0].course, "B.tech");
	strcpy( s[0].sem, "6");
	strcpy( s[0].sec, "B");
	strcpy( s[0].sub, "ICT02");
	strcpy( s[0].marks, "89");

	strcpy( s[1].regno, "2345");
	strcpy( s[1].name, "AAA");
	strcpy( s[1].add, "Block 16, MIT");
	strcpy( s[1].dept, "CSE");
	strcpy( s[1].course, "B.tech");
	strcpy( s[1].sem, "5");
	strcpy( s[1].sec, "B");
	strcpy( s[1].sub, "ICT03");
	strcpy( s[1].marks, "79");

	strcpy( s[2].regno, "3456");
	strcpy( s[2].name, "QWE");
	strcpy( s[2].add, "Block 10, KMC");
	strcpy( s[2].dept, "BioTech");
	strcpy( s[2].course, "MBBS");
	strcpy( s[2].sem, "6");
	strcpy( s[2].sec, "A");
	strcpy( s[2].sub, "ICT04");
	strcpy( s[2].marks, "99");

	int sockfd,newsockfd,ret;
 	socklen_t actuallen; 
	struct sockaddr_in serveraddr,clientaddr; 
	char buff[MAXSIZE];
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd==-1)
	{
		printf("\nSocket creation error.\n");
		exit(-1);
	} 
	serveraddr.sin_family=AF_INET; 
	serveraddr.sin_port=htons(7640); 
	serveraddr.sin_addr.s_addr=htonl(INADDR_ANY); 
	ret=bind(sockfd,(struct sockaddr*)&serveraddr,sizeof(serveraddr));
	if(ret==-1)
	{
		printf("\nBinding error.\n");
		close(sockfd);
		exit(0);
	}
	ret=listen(sockfd,1);
	if(ret==-1)
	{
		close(sockfd);
		exit(0);
	} 
	actuallen=sizeof(clientaddr); 
	newsockfd=accept(sockfd,(struct sockaddr*)&clientaddr,&actuallen);
	if(newsockfd==-1)
	{
		close(sockfd);
		exit(0);
	}

	int pid=fork(), iter=0;
	do
	{
		if(pid==0)
		{
			if(getppid()==1)
			break;
			ret=recv(newsockfd,buff,sizeof(buff),0);
			char l = buff[strlen(buff)-1];
			if(l=='r'){
				buff[strlen(buff)-1] = '\0';
				printf("Client: Registration number : %s \n",buff);
				for(iter=0; iter<3; iter++){
					if(!strcmp(s[iter].regno, buff)){
						printf("\nStudent found\n");
						ret=send(newsockfd,s[iter].name,sizeof(s[iter].name),0);
						ret=send(newsockfd,s[iter].add,sizeof(s[iter].add),0);			
					}else{
						printf("Record does not exists");
					}				
				}
			}
			if(ret==-1)
			{
				close(sockfd);
				exit(0);
			}
			if(strcmp(buff,ex)==0)
			break;

			if(l=='n'){
				buff[strlen(buff)-1] = '\0';
				printf("Client: Student name : %s \n",buff);

				for(iter=0; iter<3; iter++){
					if(!strcmp(s[iter].name, buff)){
						printf("\nStudent found\n");
						char data[50];
						strcpy(data, s[iter].name);
						strcat(data, "\nSem :");
						strcat(data, s[iter].sem);
						strcat(data, "\nDept :");
						strcat(data, s[iter].dept);
						strcat(data, "\nCourse :");
						strcat(data, s[iter].course);									
						ret=send(newsockfd,data,sizeof(data),0);
									
					}else{
						printf("Record does not exists");
					}				
				}

			}
			if(l=='s'){
				buff[strlen(buff)-1] = '\0';
				printf("Client: Subject code : %s \n",buff);
					printf("checking for subject");				
				for(iter=0; iter<3; iter++){

					if(!strcmp(s[iter].sub, buff)){
						printf("\nStudent found\n");
						printf("\n %s \n", s[iter].marks);
						ret=send(newsockfd,s[iter].marks,sizeof(s[iter].marks),0);
								
					}else{
						printf("Record does not exists");
					}				
				}
			}

			if(ret==-1)
			{ 
				perror("ERROR");
				break;
			}
			//printf("%s \n",buff); 

			ret=send(newsockfd,buff,sizeof(buff),0);
			if(ret==-1)
			{
				close(sockfd);
				exit(0);
			}
			if(strcmp(buff,ex)==0)
			break;

			if(strcmp(buff,ex)==0)
			{
				printf("\nClient terminated the chat\n");
				break;
			}
		}
		else
		{
			
			scanf(" %[^\n]",buff);			
			ret=send(newsockfd,buff,sizeof(buff),0);
			if(ret==-1)
			{
				close(sockfd);
				exit(0);
			}
			if(strcmp(buff,ex)==0)
			break;
		}
  	}
  	while(1);
	
	if(pid!=0)
	kill(pid,0);
	close(newsockfd);
	close(sockfd);
	exit(0); 
}

