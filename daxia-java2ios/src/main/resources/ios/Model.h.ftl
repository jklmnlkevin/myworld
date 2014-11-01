//
//  ${Model}.h
//  DaXiaProject
//  ***hello ios***
//  Created on 14-10-31.
//  Copyright (c) 2014年 None. All rights reserved.
//

#import "JSONModel.h"

@interface ${Model} : JSONModel

<#list fields as f>
@property (nonatomic, strong) ${f.type} *${f.name};
</#list>

@end
