
insert into img_meta (img_name,suffix,create_id,create_time,ip)
    select img_name,suffix,create_id,create_time,ip from img_map;

insert into img_local_storage_info (img_name,prefix_path,suffix)
    select img_name,prefix_path,suffix from img_map;

