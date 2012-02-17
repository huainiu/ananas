#ifndef __anIObject_h__
#define __anIObject_h__

/******************************************************************
 * type
 * */

typedef char an_int8;
typedef short an_int16;
typedef long an_int32;
typedef long long an_int64;

typedef unsigned char an_uint8;
typedef unsigned short an_uint16;
typedef unsigned long an_uint32;
typedef unsigned long long an_uint64;

typedef an_int8 an_byte;
typedef an_int16 an_short;
typedef an_int32 an_int;
typedef an_int64 an_long;

typedef an_int32 an_bool;
typedef an_uint16 an_char;
typedef an_int32 an_result;
typedef an_int32 an_refcnt;

/******************************************************************
 * define
 * */

#ifndef TRUE
#define TRUE      1
#endif

#ifndef FALSE
#define FALSE     0
#endif

#ifndef NULL
#define NULL      0
#endif

/******************************************************************
 * structure
 * */

typedef struct t_an_uuid {
	an_uint32 n0;
	an_uint16 n1;
	an_uint16 n2;
	an_uint8 n3[8];

	an_bool isEqual(t_an_uuid & another);

} an_uuid;

typedef struct t_an_iid: an_uuid {
} an_iid;

typedef struct t_an_cid: an_uuid {
} an_cid;

/******************************************************************
 * class
 * */

class aniClass;
class aniString;

class aniObject {

public:
	aniObject() {
	}
	virtual ~aniObject() {
	}

public:

	static aniClass * theClass();

public:

	virtual an_result queryInterface(aniClass * aClass, void ** pptr) = 0;
	virtual void retain() = 0;
	virtual void release() = 0;
	virtual void autorelease() = 0;

	virtual aniClass * getClass() = 0;
	virtual aniString * toString() = 0;
	virtual an_int hash() = 0;
	virtual an_bool isEqual(aniObject * another) = 0;

};

class ancObject: public virtual aniObject {

public:

	ancObject();
	virtual ~ancObject();

public:

	static aniClass * theClass();

public:

	virtual an_result queryInterface(aniClass * aClass, void ** pptr);
	virtual void retain();
	virtual void release();
	virtual void autorelease();

	virtual aniClass * getClass();
	virtual aniString * toString();
	virtual an_int hash();
	virtual an_bool isEqual(aniObject * another);

private:

	an_refcnt mRefcnt;
};

class anuObject {
public:
	static void safe_set_ptr(void ** pptr, aniObject * pnew);
	static aniObject * safe_swap(void ** pptr, aniObject * pnew);
	static an_refcnt safe_inc(an_refcnt * num, an_refcnt delta);
};

/******************************************************************
 * pointer
 * */

template<class T>
class an_ptr {

private:

	T * mp;

public:

	an_ptr() :
			mp(NULL) {
	}
	an_ptr(T * p) :
			mp(NULL) {
		if (p) {
			p->retain();
			mp = p;
		}
	}
	an_ptr(const an_ptr & ptr) :
			mp(NULL) {
		T * p = ptr.mp;
		if (p) {
			p->retain();
			mp = p;
		}
	}

public:

	virtual ~ an_ptr() {
		if (mp) {
			mp->release();
			mp = NULL;
		}
	}

public:

	operator T *() const {
		return mp;
	}

	bool isNotNull() const {
		return (mp != NULL);
	}

	const an_ptr & operator=(const an_ptr & ptr) {
		T * pold = mp;
		T * pnew = ptr.mp;

		if (pnew) {
			pnew->retain();
		}
		if (pold) {
			pold->release();
		}

		return (*this);
	}

};

/******************************************************************
 * 0         1         2         3         4         5         6
 * 0123456789012345678901234567890123456789012345678901234567890123
 * EOF
 * */

#endif // __anIObject_h__
