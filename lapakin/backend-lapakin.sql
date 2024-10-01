drop database lapakin;
create database lapakin;
use lapakin;

create table t_admin(
    id varchar(12) primary key,
    email varchar(50) unique,
    password text,
    nama varchar(30),
    no_hp varchar(15)
    );

insert into t_admin values(
  left(uuid(),12),
  "administrator@lapakin.com",
  md5("root"),
  "administrator@lapakin.com",
  "082170299091"
);

create table t_member(
    id varchar(12) primary key not null,
    email varchar(50) unique not null,
    password text not null,
    nama varchar(25),
    alamat text,
    no_hp varchar(13)
    );

insert into t_member values(
    "user001",
    "user01@gmail.com",
    md5("user01"),
    "user 01",
    "Jln. pertama no 01, Rumbai",
    "082170299091"
    );

create table t_toko(
    id varchar(12) primary key not null,
    email varchar(50) unique not null,
    password text not null,
    nama varchar(25),
    pemilik varchar(25),
    deskripsi text,
    alamat text,
    no_hp varchar(13),
    koordinat text
    );

insert into t_toko values(
    "toko001",
      "toko01@gmail.com",
    md5("toko01"),
    "toko 01",
    "toko pertama lapakin",
    "Jln, pertama no 01, Rumbai",
    "082170299091",
    ""
    );
insert into t_toko values(
    "toko002",
    "toko02@gmail.com",
    md5("toko01"),
    "serba ada",
    "toko serba ada lapakin",
    "Jln, pertama no 02, Rumbai",
    "082170299091",
    ""
    );

-- create table t_kat_produk(
--     id_kat_produk varchar(5) primary key,
--     kategori_produk varchar(20)
--     );
--
-- insert into t_kat_produk(
--     ""
-- )

create table t_produk(
    id_produk varchar(10) primary key,
    id_toko varchar(12),
    nama varchar(50),
    deskripsi text,
    stok int(4),
    harga int(8),
    satuan varchar(15),
    foreign key(id_toko) references t_toko(id)
    );

-- insert into t_kategori_produk values("kat01","testKat");

insert into t_produk values (left(uuid(),10),"toko001","beras","beras kelas 1",100,10000,"kg");
insert into t_produk values (left(uuid(),10),"toko001","gula","gula kelas 1",100,14000,"kg");
insert into t_produk values (left(uuid(),10),"toko001","cabe","cabe kelas 1",100,12000,"kg");
insert into t_produk values (left(uuid(),10),"toko002","beras","beras kelas 1",43,9500,"kg");

  create table t_transaksi(
    no_transaksi varchar(10) primary key,
    id_toko varchar(12),
    id_member varchar(12),
    tgl_transaksi date,
    status varchar(10),
    foreign key(id_toko) references t_toko(id),
    foreign key(id_member) references t_member(id)
  );

  create table t_det_transaksi(
    no_transaksi varchar(10),
    id_produk varchar(10),
    jumlah int(3),
    foreign key (no_transaksi) references t_transaksi(no_transaksi),
    foreign key (id_produk) references t_produk(id_produk)
  );
