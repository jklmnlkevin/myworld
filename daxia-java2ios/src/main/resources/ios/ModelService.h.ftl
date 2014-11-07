
#import <Foundation/Foundation.h>
#import "${Model}.h"
#import "YcAPI.h"
#import "MJExtension.h"
#import "BaseService.h"

typedef void (^done)(NSMutableArray *array);

@interface ${Model}Service : BaseService

<#list apiTests as api>
/*
// 名称：${api.name}
// 描述：${api.description?trim}
// url: 
//    ${api.url}
// example url: 
//    ${api.exampleUrl}
// 参数：
	<#list api.apiTestParameters as p>
//   ${p.name}: ${p.description}，是否必须：${p.required ? string('是', '否')}
	</#list>*/
- (void)${api.method}${api.args};

</#list>
@end