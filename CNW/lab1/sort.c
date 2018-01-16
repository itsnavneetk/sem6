#include <stdio.h>

void divide(int a[], int i, int j){
	int mid;
    if(i<j){
        mid=(i+j)/2;
        divide(a,i,mid);       
        divide(a,mid+1,j);    
        merge11(a,i,mid,mid+1,j);   
    }
}
void merge11(int a[],int i1,int j1,int i2,int j2){
	int temp[50];    
    int i,j,k;
    i=i1;    
    j=i2;    
    k=0;
    
    while(i<=j1 && j<=j2){
        if(a[i]<a[j])
            temp[k++]=a[i++];
        else
            temp[k++]=a[j++];
    }
    while(i<=j1)    
        temp[k++]=a[i++];
    while(j<=j2)    
        temp[k++]=a[j++];
    for(i=i1,j=0;i<=j2;i++,j++)
        a[i]=temp[j];
}
int main(){
	int i, key=0, flag=0, n;
	printf("Enter size of array \n");
	scanf("%d", &n);
	int a[n];
	printf("Enter array \n");
	for(i=0; i<n; i++)
		scanf("%d", &a[i]);
	printf("Entered array \n");
	for(i=0; i<n; i++)
		printf("%d ", a[i]);
	divide(a, 0, n-1);
	printf("\n Sorted array \n");
	for(i=0; i<n; i++)
		printf("%d ", a[i]);
	}