use mypocketdbrepository;


CREATE TABLE wallet (
	id_wallet int(15) NOT NULL AUTO_INCREMENT,
    primary key(id_wallet)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

CREATE TABLE user (
	id_user int(15) NOT NULL auto_increment,
    user_name varchar(256) default NULL,
    user_password varchar(256) default NULL,
    id_wallet int(15) default NULL,
    primary key(id_user),
    constraint FK_WALLET_IN_USER foreign key (id_wallet) references wallet (id_wallet)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

CREATE TABLE account (
	id_account int(20) NOT NULL AUTO_INCREMENT,
    id_wallet int(15) default NULL,
    account_name varchar(50) default NULL,
    primary key (id_account),
    constraint FK_WALLET_IN_ACCOUNT foreign key (id_wallet) references wallet (id_wallet)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

CREATE TABLE revenue_category (
	id_revenue_category int(25) NOT NULL auto_increment,
    id_wallet int(15) default NULL,
    revenue_category_name varchar(50) default null,
    primary key (id_revenue_category),
    constraint FK_WALLET_IN_REVENUE_CATEGORY foreign key (id_wallet) references wallet(id_wallet)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

CREATE TABLE expense_category (
	id_expense_category int(25) NOT NULL auto_increment,
    id_wallet int(15) default NULL,
    expense_category_name varchar(50) default NULL,
    primary key(id_expense_category),
    constraint FK_WALLET_IN_EXPENSE_CATEGORY foreign key (id_wallet) references wallet(id_wallet)
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

CREATE TABLE expense (
	id_expense int(50) NOT NULL AUTO_INCREMENT,
    id_account int(20) default NULL,
    id_expense_category int(25) default NULL,
    value double(20,2) default NULL,
    date date default NULL,
    description varchar(500) default NULL,
    primary key(id_expense),
    constraint FK_ACCOUNT_IN_EXPENSE foreign key (id_account) references account(id_account),
    constraint FK_EXPENSE_CATEGORY_IN_EXPENSE foreign key (id_expense_category) references expense_category(id_expense_category)
)ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

CREATE TABLE revenue (
	id_revenue int(50) NOT NULL auto_increment,
    id_account int(20) default NULL,
    id_revenue_category int(25) default NULL,
    value double(20,2) default NULL,
    date date default NULL,
    description varchar(500) default NULL,
    primary key(id_revenue),
    constraint FK_ACCOUNT_IN_REVENUE foreign key (id_account) references account(id_account),
	constraint FK_REVENUE_CATEGORY_IN_REVENUE foreign key (id_revenue_category) references revenue_category(id_revenue_category)
)ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

