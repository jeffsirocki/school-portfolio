void * test1(){

	//fill up memory in its entirety
	
	int d = allocateNewInt();
	
	if(d == -1){
	printf("Test 1 Complete!\n");
}

void * test2(){
	int a;
	vAddr indexes[1000];
	
	for(a=0; a < 26; a++){
		indexes[a] = allocateNewInt();
	}
	
	int *value = accessIntPtr(indexes[26]);	
	
	if(value == NULL){
		printf("Test 2 Complete!\n");
	}
}