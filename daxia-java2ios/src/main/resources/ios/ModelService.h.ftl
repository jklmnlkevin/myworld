
#import <Foundation/Foundation.h>
#import "Brand.h"
#import "YcAPI.h"

@interface ${Model}Service : NSObject

<#list apiTests as api>
// 名称：${api.name}
// 描述：${api.description}
// url: 
//    ${api.url}
// example url: 
//    ${api.exampleUrl}
// 参数：
	<#list api.apiTestParameters as p>
//   ${p.name}: ${p.description}，是否必须：${p.required ? string('是', '否')}
	</#list>
- (NSMutableArray *)${api.method}${api.args};

</#list>
@end