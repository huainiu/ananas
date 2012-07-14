//
//  ANObject.h
//  ananas
//
//  Created by xukun on 11-11-9.
//  Copyright 2011 ananas-project.org . All rights reserved.
//

#import <Foundation/Foundation.h>



@protocol ANClass;




#define CLASS_CAST(_class_,_obj_)			((_class_ *)     [ANObject castObject:(_obj_) toClass:(_class_) inLine:(__LINE__) ofFile:(__FILE__)])

#define PROTOCOL_CAST(_protocol_,_obj_)		((id<_protocol_>)[ANObject castObject:(_obj_) toProtocol:(_protocol_) inLine:(__LINE__) ofFile:(__FILE__)])




@protocol ANObject<NSObject>
- (id<ANClass>) getClass;
@end




@interface ANObject : NSObject<ANObject> {
}
+ (id) castObject:(id)aObject toClass:(Class)aClass inLine:(int)aLine ofFile:(NSString *)aFileName;
+ (id) castObject:(id)aObject toProtocol:(Protocol *)aProtocol inLine:(int)aLine ofFile:(NSString *)aFileName;
@end



