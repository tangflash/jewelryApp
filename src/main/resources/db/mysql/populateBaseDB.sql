INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 1, '01', '0','δ�ύ','����״̬' 
from dual where not exists(select 1 from baseData where TypeNum='01' and Number='0');
INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 2, '01', '1','�ύ','����״̬' 
from dual where not exists(select 1 from baseData where TypeNum='01' and Number='1');

INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 3, '02', '1','�����ӹ���','' 
from dual where not exists(select 1 from baseData where TypeNum='02' and Number='1');

INSERT INTO baseData(Id,TypeNum,Number,Name,Remark) select 4, '03', '1','���','' 
from dual where not exists(select 1 from baseData where TypeNum='03' and Number='1');