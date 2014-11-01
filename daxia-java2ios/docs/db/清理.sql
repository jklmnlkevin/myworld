DROP TABLE IF EXISTS advise;
DROP TABLE IF EXISTS `advisereply`;
DROP TABLE IF EXISTS `article`;
DROP TABLE IF EXISTS `building`;
DROP TABLE IF EXISTS `businesses`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `community`;
DROP TABLE IF EXISTS `communityaddapply`;
DROP TABLE IF EXISTS `convenience`;
DROP TABLE IF EXISTS `doorplate`; ;
DROP TABLE IF EXISTS `feeitem`;
DROP TABLE IF EXISTS `housekeeping`;
DROP TABLE IF EXISTS `keyword`;
DROP TABLE IF EXISTS `nature`;
DROP TABLE IF EXISTS `notice`;
DROP TABLE IF EXISTS `noticereply`;
DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `orderitem`;
DROP TABLE IF EXISTS `payrecord`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `questionreply`;
DROP TABLE IF EXISTS `repair`;
DROP TABLE IF EXISTS `repair_state`;
DROP TABLE IF EXISTS `repairhistory`;
DROP TABLE IF EXISTS `repairreply`;
DROP TABLE IF EXISTS `reply`;
DROP TABLE IF EXISTS `residential_quarters`;
DROP TABLE IF EXISTS `servicetype`;
DROP TABLE IF EXISTS `store`;
DROP TABLE IF EXISTS `systemmessage`;
DROP TABLE IF EXISTS `topic`;
DROP TABLE IF EXISTS `topicreply`;
DELETE FROM menu WHERE id > 154;

DELETE FROM authority
#select * from authority
WHERE `code` NOT LIKE 'system%'
AND `code` NOT LIKE 'api%'
AND `code` NOT LIKE 'user%'
AND `code` NOT LIKE 'manager%'
AND `code` NOT LIKE 'city%'
AND `code` NOT LIKE 'province%'
AND `code` NOT LIKE 'district%';

DELETE FROM USER;
DELETE FROM `systemlog`;

DELETE FROM `roleauthority`
WHERE role_id NOT IN (SELECT id FROM role);

DELETE FROM `roleauthority`
WHERE authority_id NOT IN (SELECT id FROM authority);

INSERT INTO roleauthority(role_id, authority_id)
SELECT 1, id
FROM authority;

