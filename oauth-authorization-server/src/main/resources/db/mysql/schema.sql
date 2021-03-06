create table oauth_client_details (client_id varchar(255) not null primary key,
								   client_secret varchar(255) not null,
								   resource_ids varchar(255) default null,
								   scope varchar(255) default null,
								   authorized_grant_types varchar(255) default null,
								   web_server_redirect_uri varchar(255) default null,
								   authorities varchar(255) default null,
								   access_token_validity int(11) default null,
								   refresh_token_validity int(11) default null,
								   additional_information varchar(4096) default null,
								   autoapprove varchar(255) default null);