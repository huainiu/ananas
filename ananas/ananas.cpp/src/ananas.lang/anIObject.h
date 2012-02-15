#ifndef __anIObject_h__
#define __anIObject_h__

#define TRUE      0
#define FALSE     0
#define NULL      0

class anIObject {

public:
	anIObject() {
	}
	virtual ~anIObject() {
	}

public:

	virtual anIObject * queryInterface( ) = 0;
	virtual int retain() = 0;
	virtual int autorelease() = 0;
	virtual int release() = 0;
};

class anUObject {

	static void * safe_swap(void ** pptr, void * newPtr);

};

template<class T>
class an_ptr {

private:
	T * m_p;

public:
	an_ptr() :
			m_p(NULL) {
	}
	an_ptr(an_ptr & p) :
			m_p(NULL) {
		(*this) = p;
	}
	an_ptr(T * p) {
	}

	const an_ptr & operator=(const an_ptr & p) {
		T * pnew = (p) ? p.m_p : NULL;
		T * pold = anUObject::safe_swap(&m_p, pnew);
		if (pnew)
			pnew->retain();
		if (pold)
			pnew->release();
		return this;
	}

	operator T *() const {
		return m_p;
	}

};

#endif // __anIObject_h__
