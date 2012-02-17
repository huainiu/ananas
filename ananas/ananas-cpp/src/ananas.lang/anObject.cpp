#include "anObject.h"
#include "anClass.h"

#include <stdio.h>

ancObject::ancObject() :
		mRefcnt(0) {

	printf("new object:%d\n", (int) this);
}

ancObject::~ancObject() {

	printf("del object:%d\n", (int) this);
}

an_result ancObject::queryInterface(aniClass *aClass, void**pptr) {
	return false;
}

void ancObject::retain() {
	anuObject::safe_inc(&mRefcnt, 1);
}

void ancObject::release() {
	an_refcnt rlt = anuObject::safe_inc(&mRefcnt, -1);
	if (rlt == 0) {
		delete this;
	}
}

aniClass * ancObject::getClass() {
	return 0;
}

aniString * ancObject::toString() {
	return 0;
}

an_int ancObject::hash() {
	return ((an_int) this);
}

an_bool ancObject::isEqual(aniObject *another) {
	aniClass* cls = aniObject::theClass();
	aniObject * p1 = NULL;
	aniObject * p2 = NULL;
	this->queryInterface(cls, (void**) (&p1));
	another->queryInterface(cls, (void**) (&p2));
	return (p1 == p2);
}

aniClass * aniObject::theClass() {
	static ancClass cls(NULL);
	return &cls;
}

void ancObject::autorelease() {
}

aniClass * ancObject::theClass() {
	static ancClass cls(NULL);
	return &cls;
}

an_refcnt anuObject::safe_inc(an_refcnt * num, an_refcnt delta) {
	return 0;
}

void anuObject::safe_set_ptr(void**pp, aniObject*pnew) {

	if (pnew) {
		pnew->retain();
	}

	aniObject * pold = anuObject::safe_swap(pp, pnew);

	if (pold) {
		pold->release();
	}
}

aniObject * anuObject::safe_swap(void**pp, aniObject*pnew) {
	void * pold = *pp;
	*pp = pnew;
	return (aniObject *) pold;
}

