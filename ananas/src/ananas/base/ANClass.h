//
//  ANClass.h
//  ananas
//
//  Created by xukun on 11-11-9.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ANObject.h"




@protocol ANClass<ANObject>

@end




@interface ANClass : ANObject<ANClass> {
}
@end



