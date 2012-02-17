#include "anClass.h"

ancClass::ancClass(aniClass * mySuper) :
		mSuper(mySuper) {
}

ancClass::~ancClass() {
}

aniClass *ancClass::theClass() {
	static ancClass cls(super_class::theClass());
	return &cls;
}

an_result ancClass::queryInterface(aniClass* cls, void ** pp) {
	if (aniClass::theClass()->isEqual(cls)) {
		anuObject::safe_set_ptr(pp, (aniClass*) this);
		return 0;
	} else if (ancClass::theClass()->isEqual(cls)) {
		anuObject::safe_set_ptr(pp, (ancClass*) this);
		return 0;
	} else {
		return super_class::queryInterface(cls, pp);
	}
}
