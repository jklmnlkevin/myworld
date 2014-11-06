//
//  BrandAPI.m
//  DaXiaProject
//
//  Created by kevin on 14-10-31.
//  Copyright (c) 2014年 None. All rights reserved.
//

#import "${Model}.h"

@implementation ${Model}

<#list apiTests as api>
- (NSMutableArray *)${api.method}${api.args} {
	return nil;
}
</#list>

@end
