INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 1, '01', '0','δ�ύ','����״̬' 
from dual where not exists(select 1 from baseData where TypeNum='01' and Number='0');
INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 2, '01', '1','�ύ','����״̬' 
from dual where not exists(select 1 from baseData where TypeNum='01' and Number='1');

INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 3, '02', '1','�����ӹ���','' 
from dual where not exists(select 1 from baseData where TypeNum='02' and Number='1');

update baseData set Name = '������ӹ���' where id=3 and name='�����ӹ���'; 

INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 4, '03', '1','���','' 
from dual where not exists(select 1 from baseData where TypeNum='03' and Number='1');


INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 5, '04', '1','ִģ','' 
from dual where not exists(select 1 from baseData where TypeNum='04' and Number='1');
INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 6, '04', '2','��ʯ','' 
from dual where not exists(select 1 from baseData where TypeNum='04' and Number='2');
INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 7, '04', '3','ִ��','' 
from dual where not exists(select 1 from baseData where TypeNum='04' and Number='3');
INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 8, '04', '4','�׹�','' 
from dual where not exists(select 1 from baseData where TypeNum='04' and Number='4');