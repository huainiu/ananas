#ifndef __anCObject_h__
#define __anCObject_h__

#include "anObject.h"

class aniClass: public virtual aniObject {
};

class ancClass: public ancObject, public virtual aniClass {

private:

public:
	ancClass(aniClass * mySuper);
	~ ancClass();
public:

	typedef ancObject super_class;
	typedef ancClass this_class;

	static aniClass* theClass();

public:

	virtual aniClass * getClass() {
		return this_class::theClass();
	}
	virtual an_result queryInterface(aniClass * aClass, void ** pptr);

	virtual void retain() {
	}
	virtual void release() {
	}
	virtual void autorelease() {
	}

	virtual aniString * toString() {
		return super_class::toString();
	}
	virtual an_int hash() {
		return super_class::hash();
	}
	virtual an_bool isEqual(aniObject * another) {
		return super_class::isEqual(another);
	}

private:
	const aniClass * mSuper;

};

#endif // __anCObject_h__
