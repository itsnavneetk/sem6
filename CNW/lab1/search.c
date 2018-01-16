#include<stdio.h>

int main(){
	int i, key=0, flag=0, n;
	printf("Enter size of array \n");
	scanf("%d", &n);
	int arr[n];
	printf("Enter array \n");
	for(i=0; i<n; i++)
		scanf("%d", &arr[i]);
	printf("Entered array \n");
	for(i=0; i<n; i++)
		printf("%d ", arr[i]);
	printf("Enter element to be searched \n");
	scanf("%d", &key);
	//search
	for(i=0; i<n; i++){
		if(arr[i]==key){
			printf("\n Element found at %d \n", i+1);
			flag=1;
			break;
		}
	}
	if(flag==0)
		printf("Element not found \n");
}