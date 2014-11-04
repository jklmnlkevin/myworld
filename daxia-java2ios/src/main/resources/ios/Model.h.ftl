//
//  ${Model}.h
//  DaXiaProject
//  ***hello ios***
//  Created on 14-10-31.
//  Copyright (c) 2014年 None. All rights reserved.
//

#import "JSONModel.h"
<#if hasImageDTO>
#import "ImageDTO.h"
</#if>
<#if hasUser>
#import "User.h"
</#if>

@interface ${Model} : JSONModel

<#list fields as f>
<#if f.array>
@property (nonatomic, strong) NSArray *${f.name};
</#if>
<#if !f.array>
	<#if f.type == 'BOOL'>
@property (nonatomic, strong) ${f.type} ${f.name};
	</#if>
	<#if f.type != 'BOOL'>	
@property (nonatomic, strong) ${f.type} *${f.name};
	</#if>
</#if>
</#list>

@end
