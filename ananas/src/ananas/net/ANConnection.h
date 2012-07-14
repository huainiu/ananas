//
//  ANConnection.h
//  ananas
//
//  Created by xukun on 11-11-22.
//  Copyright 2011 fuyoo. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ANObject.h"
#import "ANOutputStream.h"
#import "ANInputStream.h"
#import "an_type.h"




#pragma mark -
#pragma mark basic




@protocol ANConnection <ANObject>
- (void) close;
@end




@protocol ANInputConnection <ANConnection>
- (id<ANInputStream>) openInputStream;
@end




@protocol ANOutputConnection <ANConnection>
- (id<ANOutputStream>) openOutputStream;
@end




@protocol ANStreamConnection <ANOutputStream, ANInputStream>
@end




@protocol ANContentConnection <ANStreamConnection>
- (NSString *) getEncoding;
- (an_long)    getLength;
- (NSString *) getType;
@end




#pragma mark -
#pragma mark used




@protocol ANHttpConnection <ANContentConnection>
- (NSString*) getHeaderField:(NSString*)aName;
- (NSArray*)  listHeaderFieldNames;
- (int)       getResponseCode;
- (NSString*) getResponseMessage;
- (NSString*) getURL;
- (void) setRequestMethod:(NSString*)aMethod;
- (void) setRequestPropertyKey:(NSString*)aKey Value:(NSString*)aValue;
@end




@protocol ANFileConnection <ANContentConnection>
- (id<ANFileConnection>) getParent;
- (NSArray *) listChildren;
- (BOOL) isExisted;
- (BOOL) is;
@end




@protocol ANSocketConnection <ANStreamConnection>
@end




@protocol ANServerSocketConnection <ANConnection>
@end



