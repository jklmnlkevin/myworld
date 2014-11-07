//
//  BrandAPI.m
//  DaXiaProject
//
//  Created by kevin on 14-10-31.
//  Copyright (c) 2014年 None. All rights reserved.
//

#import "${Model}Service.h"

@implementation ${Model}Service

<#list apiTests as api>
- (void)${api.method}${api.args} {
	NSMutableDictionary *parameters = [[NSMutableDictionary alloc] init];
	<#list api.apiTestParameters as p>
    if (${p.name?replace('.','__')} != nil) {
        [parameters setObject:${p.name?replace('.','__')} forKey:@"${p.name}"];
    }
    </#list>
	<#if api.method == 'list'>
    if (pageNum != nil) {
        [parameters setObject:pageNum forKey:@"pageNum"];
    }
    if (numPerPage != nil) {
        [parameters setObject:numPerPage forKey:@"numPerPage"];
    }
	</#if>
        
    [[YcAPI sharedInstance] callAPI2:@"${api.url}" parameters:parameters withCompletion:^(BOOL completed, NSDictionary *data) {
    	<#if api.method == 'list'>    
        NSArray *infos = data[@"${model}Infos"];
        NSMutableArray *array = [[NSMutableArray alloc] init];
        for (int i = 0; i < infos.count; i++) {
            ${Model} *${model} = [${Model} objectWithKeyValues:infos[i]];
            [array addObject:${model}];
        }
        done(array);
        </#if>
        <#if api.method != 'list'>    
        </#if>                      
    }];
	
}
</#list>

@end
