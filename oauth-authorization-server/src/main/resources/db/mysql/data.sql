INSERT INTO OAUTH_CLIENT_DETAILS (CLIENT_ID, CLIENT_SECRET, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES,
								  WEB_SERVER_REDIRECT_URI, AUTHORITIES,	ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY,
								  ADDITIONAL_INFORMATION, AUTOAPPROVE)
	 VALUES ('client_app_1', '$2a$10$DDo8OaImrqM5zEKnWGkvyuqBduxLFIbuQ6U0whCJNksS8KQVzYiJS',
			 'USER_CLIENT_RESOURCE, USER_ADMIN_RESOURCE',
			 'foo, read, write',
			 'authorization_code,refresh_token',
			 'http://localhost:4200/', NULL,
			 900, 3600,
			 '{}', true);
			 
INSERT INTO OAUTH_CLIENT_DETAILS (CLIENT_ID, CLIENT_SECRET, RESOURCE_IDS, SCOPE, AUTHORIZED_GRANT_TYPES,
								  WEB_SERVER_REDIRECT_URI, AUTHORITIES,	ACCESS_TOKEN_VALIDITY, REFRESH_TOKEN_VALIDITY,
								  ADDITIONAL_INFORMATION, AUTOAPPROVE)
	 VALUES ('client_app_2', '$2a$10$h8peZKrY/qC8/9f4K8E7L.sxB/0MttQ1allHCAeygtHVV4MsudzZi',
			 'USER_CLIENT_RESOURCE, USER_ADMIN_RESOURCE',
			 'role_admin,role_user',
			 'password,refresh_token',
			 NULL, NULL,
			 900, 3600,
			 '{}', true);

INSERT INTO USER (USERNAME, PASSWORD, EMAIL, ENABLED, ACCOUNT_EXPIRED, CREDENTIALS_EXPIRED, ACCOUNT_LOCKED)
     VALUES ('admin', '$2a$10$DPcqbUNdHkymIxM0oG1/oOmV8Zi.GqdCdnpM3/hmHqY8SGqMxb/6u', 'admin@padrao.com.br', 1, 0, 0, 0);
     
INSERT INTO USER (USERNAME, PASSWORD, EMAIL, ENABLED, ACCOUNT_EXPIRED, CREDENTIALS_EXPIRED, ACCOUNT_LOCKED)
     VALUES ('user', '$2a$10$eKr1vSRf5UgjBbc1mjdpNOzKwz44qETjklghDAHh/sy//VINNaN5q', 'user@padrao.com.br', 1, 0, 0, 0);
     
INSERT INTO ROLE (NAME)
     VALUES ('role_admin'), ('role_user');
     
INSERT INTO PERMISSION (NAME)
     VALUES ('can_create_user'), ('can_update_user'), ('can_read_user'), ('can_delete_user');
     
INSERT INTO PERMISSION_ROLE (PERMISSION_ID, ROLE_ID)
     VALUES (1, 1), /* can_create_user assigned to role_admin */
		    (2, 1), /* can_update_user assigned to role_admin */
			(3, 1), /* can_read_user assigned to role_admin */
			(4, 1), /* can_delete_user assigned to role_admin */
			(3, 2);  /* can_read_user assigned to role_user */
     
INSERT INTO ROLE_USER (ROLE_ID, USER_ID)
     VALUES (1, 1), /* role_admin assigned to admin user */
			(2, 2); /* role_user assigned to user user */