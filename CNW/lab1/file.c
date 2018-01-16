#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    FILE *fptr;
    char sentence[1000]={'\0'};
   fptr = fopen("new.txt", "w");
   if(fptr == NULL)
   {
      printf("Error!");
      exit(1);
   }
   printf("Enter a sentence:\n");
        gets(sentence);
   fprintf(fptr,"%s", sentence);
   fclose(fptr);
   
   //read
    printf("\nReading from created file :: \n \n");
    char ch;
    fptr = fopen("new.txt", "r");
    if (fptr == NULL)    {
        printf("Cannot open file \n");
        exit(0);
    }
    ch = fgetc(fptr);
    while (ch != EOF){
        printf ("%c", ch);
        ch = fgetc(fptr);
    }
    fclose(fptr);
     printf("\n");
        
     //search
        FILE *fp;
        char filename[]="new.txt",line[200],search_string[10];
        printf("Enter a word to be searched \n");
        gets(search_string);
        fp=fopen(filename,"r");
        if(!fp){
                exit(0);
        }
        while ( fgets ( line, 200, fp ) != NULL ){
                if(strstr(line,search_string)){
                printf("\nWord found in line \n\n");
                fputs ( line, stdout ); /* write the line */
                }else
                    printf("could not find the word \n");
        }
        printf("\n");
        fclose ( fp );
        return 0;
}