CREATE DATABASE `db_yolo` WITH OWNER = postgres ENCODING = 'UTF8' TABLESPACE = pg_default LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252' CONNECTION LIMIT = -1;
```

## 1. Create a new people

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_people` (
  `id` BINARY(16) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` varchar(1) DEFAULT "N" NOT NULL,
  `birthdate` date NOT NULL,
  `age` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT ck_people CHECK (gender IN ("F", "M", "N")),
  CONSTRAINT pk_people PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 2. Create a new phone

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_phones` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `phone` varchar(45) NOT NULL,
  `person_id` binary(16) NOT NULL,
  CONSTRAINT pk_phones PRIMARY KEY (`id`),
  KEY `fk_phones_persons_idx` (`person_id`),
  CONSTRAINT `fk_phones_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 3. Create a new email

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_emails` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `person_id` binary(16) NOT NULL,
  CONSTRAINT pk_emails PRIMARY KEY (`id`),
  KEY `fk_emails_persons_idx` (`person_id`),
  CONSTRAINT `fk_emails_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 4. Create a new address

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_addresses` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  -- `address` varchar(45) NOT NULL,
  `street` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `zip` varchar(100) NOT NULL,
  `person_id` binary(16) NOT NULL,
  CONSTRAINT pk_address PRIMARY KEY (`id`),
  KEY `fk_addresses_persons_idx` (`person_id`),
  CONSTRAINT `fk_addresses_persons` FOREIGN KEY (`person_id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2001 DEFAULT CHARSET=utf8;
```

## 5. Create a new user

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_users` (
  `id` binary(16) NOT NULL,
  `avatar` LONGBLOB NOT NULL,
  `background` LONGBLOB NOT NULL,
  `username` varchar(45) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `fk_users_people` FOREIGN KEY (`id`) REFERENCES `tbl_people` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 6. Create a new role

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_roles` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  CONSTRAINT pk_roles PRIMARY KEY (`id`),
  UNIQUE KEY `role_UNIQUE` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 7. Create a new user_role

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  CONSTRAINT pk_user_roles PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_user_roles_roles_idx` (`role_id`),
  CONSTRAINT `fk_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `tbl_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## 8. Create a new token

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_tokens` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expiry_date` datetime NOT NULL,
  CONSTRAINT pk_tokens PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `fk_tokens_users_idx` (`user_id`),
  CONSTRAINT `fk_tokens_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 9. Create a new post

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_posts` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_posts PRIMARY KEY (`id`),
  KEY `fk_posts_users_idx` (`user_id`),
  CONSTRAINT `fk_posts_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 10. Create a new comment

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_comments` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_comments PRIMARY KEY (`id`),
  KEY `fk_comments_users_idx` (`user_id`),
  KEY `fk_comments_posts_idx` (`post_id`),
  CONSTRAINT `fk_comments_posts` FOREIGN KEY (`post_id`) REFERENCES `tbl_posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 11. Create a new like

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_likes` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  CONSTRAINT pk_likes PRIMARY KEY (`id`),
  KEY `fk_likes_users_idx` (`user_id`),
  KEY `fk_likes_posts_idx` (`post_id`),
  CONSTRAINT `fk_likes_posts` FOREIGN KEY (`post_id`) REFERENCES `tbl_posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_likes_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 12. Create a new follow

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_follows` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  CONSTRAINT pk_follows PRIMARY KEY (`id`),
  KEY `fk_follows_users_idx` (`user_id`),
  KEY `fk_follows_users1_idx` (`follower_id`),
  CONSTRAINT `fk_follows_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_follows_users1` FOREIGN KEY (`follower_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 13. Create a new notification

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_notifications` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follower_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `like_id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `is_read` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_notifications PRIMARY KEY (`id`),
  KEY `fk_notifications_users_idx` (`user_id`),
  KEY `fk_notifications_users1_idx` (`follower_id`),
  KEY `fk_notifications_posts_idx` (`post_id`),
  KEY `fk_notifications_comments_idx` (`comment_id`),
  KEY `fk_notifications_likes_idx` (`like_id`),
  CONSTRAINT `fk_notifications_comments` FOREIGN KEY (`comment_id`) REFERENCES `tbl_comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_likes` FOREIGN KEY (`like_id`) REFERENCES `tbl_likes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_posts` FOREIGN KEY (`post_id`) REFERENCES `tbl_posts` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_notifications_users1` FOREIGN KEY (`follower_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 14. Create a new chat

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chats` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `is_read` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chats PRIMARY KEY (`id`),
  KEY `fk_chats_users_idx` (`user_id`),
  KEY `fk_chats_users1_idx` (`receiver_id`),
  CONSTRAINT `fk_chats_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chats_users1` FOREIGN KEY (`receiver_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 15. Create a new chat list

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_lists` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `last_message` text NOT NULL,
  `is_read` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_lists PRIMARY KEY (`id`),
  KEY `fk_chat_lists_users_idx` (`user_id`),
  KEY `fk_chat_lists_users1_idx` (`receiver_id`),
  CONSTRAINT `fk_chat_lists_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_lists_users1` FOREIGN KEY (`receiver_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 16. Create a new chat messages

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_messages` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `is_read` tinyint(1) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_messages PRIMARY KEY (`id`),
  KEY `fk_chat_messages_chats_idx` (`chat_id`),
  KEY `fk_chat_messages_users_idx` (`user_id`),
  CONSTRAINT `fk_chat_messages_chats` FOREIGN KEY (`chat_id`) REFERENCES `tbl_chats` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_messages_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 17. Create a new chat block

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_blocks` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `block_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_blocks PRIMARY KEY (`id`),
  KEY `fk_chat_blocks_users_idx` (`user_id`),
  KEY `fk_chat_blocks_users1_idx` (`block_id`),
  CONSTRAINT `fk_chat_blocks_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_blocks_users1` FOREIGN KEY (`block_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 18. Create a new chat mute

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_mutes` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `mute_id` int(11) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_mutes PRIMARY KEY (`id`),
  KEY `fk_chat_mutes_users_idx` (`user_id`),
  KEY `fk_chat_mutes_users1_idx` (`mute_id`),
  CONSTRAINT `fk_chat_mutes_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_mutes_users1` FOREIGN KEY (`mute_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 19. Create a new chat report

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_reports` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `report_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_reports PRIMARY KEY (`id`),
  KEY `fk_chat_reports_users_idx` (`user_id`),
  KEY `fk_chat_reports_users1_idx` (`report_id`),
  CONSTRAINT `fk_chat_reports_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_reports_users1` FOREIGN KEY (`report_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 20. Create a new chat message files

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_message_files` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_message_id` int(11) NOT NULL,
  `file` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_message_files PRIMARY KEY (`id`),
  KEY `fk_chat_message_files_chat_messages_idx` (`chat_message_id`),
  CONSTRAINT `fk_chat_message_files_chat_messages` FOREIGN KEY (`chat_message_id`) REFERENCES `tbl_chat_messages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 21. Create a new chat message reactions

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_message_reactions` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_message_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reaction` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_message_reactions PRIMARY KEY (`id`),
  KEY `fk_chat_message_reactions_chat_messages_idx` (`chat_message_id`),
  KEY `fk_chat_message_reactions_users_idx` (`user_id`),
  CONSTRAINT `fk_chat_message_reactions_chat_messages` FOREIGN KEY (`chat_message_id`) REFERENCES `tbl_chat_messages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_message_reactions_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 22. Create a new chat message replies

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_message_replies` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_message_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_message_replies PRIMARY KEY (`id`),
  KEY `fk_chat_message_replies_chat_messages_idx` (`chat_message_id`),
  KEY `fk_chat_message_replies_users_idx` (`user_id`),
  CONSTRAINT `fk_chat_message_replies_chat_messages` FOREIGN KEY (`chat_message_id`) REFERENCES `tbl_chat_messages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_message_replies_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 23. Create a new chat message replies files

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_message_reply_files` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_message_reply_id` int(11) NOT NULL,
  `file` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_message_reply_files PRIMARY KEY (`id`),
  KEY `fk_chat_message_reply_files_chat_message_replies_idx` (`chat_message_reply_id`),
  CONSTRAINT `fk_chat_message_reply_files_chat_message_replies` FOREIGN KEY (`chat_message_reply_id`) REFERENCES `tbl_chat_message_replies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

## 24. Create a new chat message reply reactions

```sql
-- Path: src\main\resources\database.sql
CREATE TABLE `tbl_chat_message_reply_reactions` (
  `id` binary(16) NOT NULL AUTO_INCREMENT,
  `chat_message_reply_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reaction` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  CONSTRAINT pk_chat_message_reply_reactions PRIMARY KEY (`id`),
  KEY `fk_chat_message_reply_reactions_chat_message_replies_idx` (`chat_message_reply_id`),
  KEY `fk_chat_message_reply_reactions_users_idx` (`user_id`),
  CONSTRAINT `fk_chat_message_reply_reactions_chat_message_replies` FOREIGN KEY (`chat_message_reply_id`) REFERENCES `tbl_chat_message_replies` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_message_reply_reactions_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```
