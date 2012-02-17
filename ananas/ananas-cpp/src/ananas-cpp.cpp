//============================================================================
// Name        : ananas-cpp.cpp
// Author      : xk
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <stdio.h>
#include "ananas.lang/anObject.h"

using namespace std;

int main() {
	printf("Hello World!!!\n"); // prints Hello World!!!

	an_ptr<aniObject> obj = new ancObject();

	if (obj.isNotNull()) {

	}

	obj = NULL;
	obj = NULL;

	return 0;
}
