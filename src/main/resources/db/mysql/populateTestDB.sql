INSERT IGNORE INTO material(Id,Number,Name,Sort,Weight) VALUES (1, 'W5', 'W5',0,0);
INSERT IGNORE INTO material(Id,Number,Name,Sort,Weight) VALUES (2, 'B255', 'B255',0,0);
INSERT IGNORE INTO material(Id,Number,Name,Sort,Weight) VALUES (3, 'FM520', 'FM520',0,0);

INSERT IGNORE INTO GoldType(Id,Number,Name,Remark) VALUES (1, '18k', '18K白','');
INSERT IGNORE INTO GoldType(Id,Number,Name,Remark) VALUES (2, '19k', '19K白','');

INSERT IGNORE INTO Client(Id,Number,Name,Address,Tel,Remark) VALUES (1, '01', '网纪','广西','0773','');
INSERT IGNORE INTO Client(Id,Number,Name,Address,Tel,Remark) VALUES (2, '02', '金蝶','深圳','0755','');

INSERT IGNORE INTO Product(Id,Number,Name,Remark) VALUES (1, '01', '女戒','');
INSERT IGNORE INTO Product(Id,Number,Name,Remark) VALUES (2, '02', '情侣戒','');

INSERT IGNORE INTO ProductStyle(Id,ProductId,Number,Name,Remark) VALUES (1,1,'AT0611', 'AT0611','');
INSERT IGNORE INTO ProductStyle(Id,ProductId,Number,Name,Remark) VALUES (2,1,'AT0621', 'AT0621','');
INSERT IGNORE INTO ProductStyle(Id,ProductId,Number,Name,Remark) VALUES (3,2,'AB0473', 'AB0473','');
INSERT IGNORE INTO ProductStyle(Id,ProductId,Number,Name,Remark) VALUES (4,2,'AB0474', 'AB0474','');

INSERT IGNORE INTO materialIn(Id,BillNumber,BizDate,CreateTime) VALUES (1, '13061601', '2013-06-16','2013-06-16 18:00');
INSERT IGNORE INTO materialIn(Id,BillNumber,BizDate,CreateTime) VALUES (2, '13061602', '2013-06-16','2013-06-16 18:10');
INSERT IGNORE INTO materialIn(Id,BillNumber,BizDate,CreateTime) VALUES (3, '13061702', '2013-06-17','2013-06-17 18:30');

INSERT IGNORE INTO materialInDetail(Id,BillId,MaterId,Amount,Weight,Sort) VALUES (1, 1, 1,11,110,0);
INSERT IGNORE INTO materialInDetail(Id,BillId,MaterId,Amount,Weight,Sort) VALUES (2, 1, 2,10,100,0);

INSERT IGNORE INTO materialOut(Id,BillNumber,BizDate,CreateTime,ClientId,GoldPrice,GoldTypeId,BillStatus) VALUES (1, '13061601',
'2013-06-16','2013-06-16 18:00',1,200,1,0);
INSERT IGNORE INTO materialOut(Id,BillNumber,BizDate,CreateTime,ClientId,GoldPrice,GoldTypeId,BillStatus) VALUES (2, '13061602', 
'2013-06-16','2013-06-16 18:10',2,200,2,0);
INSERT IGNORE INTO materialOut(Id,BillNumber,BizDate,CreateTime,ClientId,GoldPrice,GoldTypeId,BillStatus) VALUES (3, '13061702',
'2013-06-17','2013-06-17 18:30',2,200,2,0);

INSERT IGNORE INTO materialOutDetail(Id,BillId,MaterId,Amount,Weight,Sort,
	ProductNameId,StyleNameId,HandSize,ProductAmount,ProductWeight,
	GoldWeight,ConsumeWeight,ProcessCost,AddProcessCost,SuperSetCost,
	FactoryAddMoney,SecMaterId,SecAmount,SecWeight,SecPrice
) 
VALUES (1, 1, 1,11,110,0
	,1,1,18.2,5,100,200,300,12.5,15.1,15
	,111,3,10,100,200
);
INSERT IGNORE INTO materialOutDetail(Id,BillId,MaterId,Amount,Weight,Sort,
	ProductNameId,StyleNameId,HandSize,ProductAmount,ProductWeight,
	GoldWeight,ConsumeWeight,ProcessCost,AddProcessCost,SuperSetCost,
	FactoryAddMoney,SecMaterId,SecAmount,SecWeight,SecPrice
) 
VALUES (2, 1, 2,10,100,0
,1,1,18.2,5,100,200,300,12.5,15.1,15
	,111,3,10,100,200
);

INSERT IGNORE INTO materialInventory(Id,ClientId,MaterId,InAmount,InWeight,OutAmount,OutWeight,Sort) 
VALUES (1, 1, 1, 0,0,0,0,0);