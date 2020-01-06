PGDMP         3                w            postgres    9.6.12    9.6.12 S    �	           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            �	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            �	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            �	           1262    12669    postgres    DATABASE     f   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'C' LC_CTYPE = 'C';
    DROP DATABASE postgres;
             postgres    false            �	           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                  postgres    false    2506                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false            �	           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    4                        3079    12655    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false            �	           0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    2                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                  false            �	           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                       false    1            �            1259    17947    account    TABLE     �   CREATE TABLE public.account (
    id_accout integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);
    DROP TABLE public.account;
       public         postgres    false    4            �            1259    17945    account_id_accout_seq    SEQUENCE     ~   CREATE SEQUENCE public.account_id_accout_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.account_id_accout_seq;
       public       postgres    false    192    4            �	           0    0    account_id_accout_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.account_id_accout_seq OWNED BY public.account.id_accout;
            public       postgres    false    191            �            1259    17977    bill    TABLE     {   CREATE TABLE public.bill (
    id_bill integer NOT NULL,
    id_checkout integer NOT NULL,
    amounts integer NOT NULL
);
    DROP TABLE public.bill;
       public         postgres    false    4            �            1259    17975    bill_id_bill_seq    SEQUENCE     y   CREATE SEQUENCE public.bill_id_bill_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.bill_id_bill_seq;
       public       postgres    false    199    4            �	           0    0    bill_id_bill_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.bill_id_bill_seq OWNED BY public.bill.id_bill;
            public       postgres    false    198            �            1259    17985    checkin    TABLE       CREATE TABLE public.checkin (
    id_checkin integer NOT NULL,
    name character varying(50) NOT NULL,
    identity_card character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    id_room integer NOT NULL
);
    DROP TABLE public.checkin;
       public         postgres    false    4            �            1259    17983    checkin_id_checkin_seq    SEQUENCE        CREATE SEQUENCE public.checkin_id_checkin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.checkin_id_checkin_seq;
       public       postgres    false    201    4            �	           0    0    checkin_id_checkin_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.checkin_id_checkin_seq OWNED BY public.checkin.id_checkin;
            public       postgres    false    200            �            1259    18001    checkout    TABLE     u  CREATE TABLE public.checkout (
    id_checkout integer NOT NULL,
    name character varying(50) NOT NULL,
    identity_card character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    id_room integer NOT NULL,
    date_checkin character varying(50) NOT NULL,
    date_checkout character varying(50) NOT NULL
);
    DROP TABLE public.checkout;
       public         postgres    false    4            �            1259    17999    checkout_id_checkout_seq    SEQUENCE     �   CREATE SEQUENCE public.checkout_id_checkout_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.checkout_id_checkout_seq;
       public       postgres    false    4    205            �	           0    0    checkout_id_checkout_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.checkout_id_checkout_seq OWNED BY public.checkout.id_checkout;
            public       postgres    false    204            �            1259    17955    customer    TABLE     �   CREATE TABLE public.customer (
    id_customer integer NOT NULL,
    name character varying(255) NOT NULL,
    identity_card character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    address character varying(50) NOT NULL
);
    DROP TABLE public.customer;
       public         postgres    false    4            �            1259    17953    customer_id_customer_seq    SEQUENCE     �   CREATE SEQUENCE public.customer_id_customer_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.customer_id_customer_seq;
       public       postgres    false    194    4            �	           0    0    customer_id_customer_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.customer_id_customer_seq OWNED BY public.customer.id_customer;
            public       postgres    false    193            �            1259    17993    customerbooked    TABLE     z  CREATE TABLE public.customerbooked (
    id_cusbooked integer NOT NULL,
    name character varying(50) NOT NULL,
    identity_card character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    id_room integer NOT NULL,
    datecheckin character varying(50) NOT NULL,
    datecheckout character varying(50) NOT NULL
);
 "   DROP TABLE public.customerbooked;
       public         postgres    false    4            �            1259    17991    customerbooked_id_cusbooked_seq    SEQUENCE     �   CREATE SEQUENCE public.customerbooked_id_cusbooked_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 6   DROP SEQUENCE public.customerbooked_id_cusbooked_seq;
       public       postgres    false    203    4            �	           0    0    customerbooked_id_cusbooked_seq    SEQUENCE OWNED BY     c   ALTER SEQUENCE public.customerbooked_id_cusbooked_seq OWNED BY public.customerbooked.id_cusbooked;
            public       postgres    false    202            �            1259    18009    employee    TABLE       CREATE TABLE public.employee (
    id_employee integer NOT NULL,
    name character varying(50) NOT NULL,
    phone character varying(50) NOT NULL,
    "position" character varying(50) NOT NULL,
    salary integer NOT NULL,
    date_start character varying(50) NOT NULL
);
    DROP TABLE public.employee;
       public         postgres    false    4            �            1259    18007    employee_id_employee_seq    SEQUENCE     �   CREATE SEQUENCE public.employee_id_employee_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.employee_id_employee_seq;
       public       postgres    false    207    4            �	           0    0    employee_id_employee_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.employee_id_employee_seq OWNED BY public.employee.id_employee;
            public       postgres    false    206            �            1259    17895    report    TABLE     �   CREATE TABLE public.report (
    id_report integer NOT NULL,
    date_create character varying(50) NOT NULL,
    amount_salary integer NOT NULL,
    amount_bill integer NOT NULL
);
    DROP TABLE public.report;
       public         postgres    false    4            �            1259    17961    room    TABLE     r   CREATE TABLE public.room (
    id_room integer NOT NULL,
    type integer NOT NULL,
    price integer NOT NULL
);
    DROP TABLE public.room;
       public         postgres    false    4            �            1259    17901    status    TABLE     �   CREATE TABLE public.status (
    id_status integer NOT NULL,
    id_room integer NOT NULL,
    status integer NOT NULL,
    date_checkin character varying(50) NOT NULL,
    date_checkout character varying(50) NOT NULL
);
    DROP TABLE public.status;
       public         postgres    false    4            �            1259    17968    tobook    TABLE     �   CREATE TABLE public.tobook (
    id_book integer NOT NULL,
    id_customer integer NOT NULL,
    id_room integer NOT NULL,
    date_checkin character varying(50) NOT NULL,
    date_checkout character varying(50) NOT NULL
);
    DROP TABLE public.tobook;
       public         postgres    false    4            �            1259    17966    tobook_id_book_seq    SEQUENCE     {   CREATE SEQUENCE public.tobook_id_book_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.tobook_id_book_seq;
       public       postgres    false    4    197            �	           0    0    tobook_id_book_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.tobook_id_book_seq OWNED BY public.tobook.id_book;
            public       postgres    false    196            �            1259    17939    user_id_seq    SEQUENCE     t   CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public       postgres    false    4            �            1259    17942    user_id_seq1    SEQUENCE     u   CREATE SEQUENCE public.user_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.user_id_seq1;
       public       postgres    false    4            �            1259    17907 	   useradmin    TABLE     �   CREATE TABLE public.useradmin (
    id_user integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);
    DROP TABLE public.useradmin;
       public         postgres    false    4            	           2604    17950    account id_accout    DEFAULT     v   ALTER TABLE ONLY public.account ALTER COLUMN id_accout SET DEFAULT nextval('public.account_id_accout_seq'::regclass);
 @   ALTER TABLE public.account ALTER COLUMN id_accout DROP DEFAULT;
       public       postgres    false    191    192    192            	           2604    17980    bill id_bill    DEFAULT     l   ALTER TABLE ONLY public.bill ALTER COLUMN id_bill SET DEFAULT nextval('public.bill_id_bill_seq'::regclass);
 ;   ALTER TABLE public.bill ALTER COLUMN id_bill DROP DEFAULT;
       public       postgres    false    199    198    199            	           2604    17988    checkin id_checkin    DEFAULT     x   ALTER TABLE ONLY public.checkin ALTER COLUMN id_checkin SET DEFAULT nextval('public.checkin_id_checkin_seq'::regclass);
 A   ALTER TABLE public.checkin ALTER COLUMN id_checkin DROP DEFAULT;
       public       postgres    false    201    200    201             	           2604    18004    checkout id_checkout    DEFAULT     |   ALTER TABLE ONLY public.checkout ALTER COLUMN id_checkout SET DEFAULT nextval('public.checkout_id_checkout_seq'::regclass);
 C   ALTER TABLE public.checkout ALTER COLUMN id_checkout DROP DEFAULT;
       public       postgres    false    204    205    205            	           2604    17958    customer id_customer    DEFAULT     |   ALTER TABLE ONLY public.customer ALTER COLUMN id_customer SET DEFAULT nextval('public.customer_id_customer_seq'::regclass);
 C   ALTER TABLE public.customer ALTER COLUMN id_customer DROP DEFAULT;
       public       postgres    false    194    193    194            	           2604    17996    customerbooked id_cusbooked    DEFAULT     �   ALTER TABLE ONLY public.customerbooked ALTER COLUMN id_cusbooked SET DEFAULT nextval('public.customerbooked_id_cusbooked_seq'::regclass);
 J   ALTER TABLE public.customerbooked ALTER COLUMN id_cusbooked DROP DEFAULT;
       public       postgres    false    203    202    203            !	           2604    18012    employee id_employee    DEFAULT     |   ALTER TABLE ONLY public.employee ALTER COLUMN id_employee SET DEFAULT nextval('public.employee_id_employee_seq'::regclass);
 C   ALTER TABLE public.employee ALTER COLUMN id_employee DROP DEFAULT;
       public       postgres    false    207    206    207            	           2604    17971    tobook id_book    DEFAULT     p   ALTER TABLE ONLY public.tobook ALTER COLUMN id_book SET DEFAULT nextval('public.tobook_id_book_seq'::regclass);
 =   ALTER TABLE public.tobook ALTER COLUMN id_book DROP DEFAULT;
       public       postgres    false    196    197    197            �	          0    17947    account 
   TABLE DATA               @   COPY public.account (id_accout, username, password) FROM stdin;
    public       postgres    false    192   1Z       �	           0    0    account_id_accout_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.account_id_accout_seq', 9, true);
            public       postgres    false    191            �	          0    17977    bill 
   TABLE DATA               =   COPY public.bill (id_bill, id_checkout, amounts) FROM stdin;
    public       postgres    false    199   �Z       �	           0    0    bill_id_bill_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.bill_id_bill_seq', 1, true);
            public       postgres    false    198            �	          0    17985    checkin 
   TABLE DATA               [   COPY public.checkin (id_checkin, name, identity_card, phone, address, id_room) FROM stdin;
    public       postgres    false    201   �Z       �	           0    0    checkin_id_checkin_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.checkin_id_checkin_seq', 5, true);
            public       postgres    false    200            �	          0    18001    checkout 
   TABLE DATA               z   COPY public.checkout (id_checkout, name, identity_card, phone, address, id_room, date_checkin, date_checkout) FROM stdin;
    public       postgres    false    205   �Z       �	           0    0    checkout_id_checkout_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.checkout_id_checkout_seq', 4, true);
            public       postgres    false    204            �	          0    17955    customer 
   TABLE DATA               T   COPY public.customer (id_customer, name, identity_card, phone, address) FROM stdin;
    public       postgres    false    194   [       �	           0    0    customer_id_customer_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.customer_id_customer_seq', 22, true);
            public       postgres    false    193            �	          0    17993    customerbooked 
   TABLE DATA                  COPY public.customerbooked (id_cusbooked, name, identity_card, phone, address, id_room, datecheckin, datecheckout) FROM stdin;
    public       postgres    false    203   B[       �	           0    0    customerbooked_id_cusbooked_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.customerbooked_id_cusbooked_seq', 2, true);
            public       postgres    false    202            �	          0    18009    employee 
   TABLE DATA               \   COPY public.employee (id_employee, name, phone, "position", salary, date_start) FROM stdin;
    public       postgres    false    207   _[       �	           0    0    employee_id_employee_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.employee_id_employee_seq', 9, true);
            public       postgres    false    206            �	          0    17895    report 
   TABLE DATA               T   COPY public.report (id_report, date_create, amount_salary, amount_bill) FROM stdin;
    public       postgres    false    186   \       �	          0    17961    room 
   TABLE DATA               4   COPY public.room (id_room, type, price) FROM stdin;
    public       postgres    false    195   I\       �	          0    17901    status 
   TABLE DATA               Y   COPY public.status (id_status, id_room, status, date_checkin, date_checkout) FROM stdin;
    public       postgres    false    187   �\       �	          0    17968    tobook 
   TABLE DATA               \   COPY public.tobook (id_book, id_customer, id_room, date_checkin, date_checkout) FROM stdin;
    public       postgres    false    197   �\       �	           0    0    tobook_id_book_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.tobook_id_book_seq', 18, true);
            public       postgres    false    196            �	           0    0    user_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.user_id_seq', 8, true);
            public       postgres    false    189            �	           0    0    user_id_seq1    SEQUENCE SET     :   SELECT pg_catalog.setval('public.user_id_seq1', 1, true);
            public       postgres    false    190            �	          0    17907 	   useradmin 
   TABLE DATA               @   COPY public.useradmin (id_user, username, password) FROM stdin;
    public       postgres    false    188   ]       )	           2606    17952    account account_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id_accout);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public         postgres    false    192    192            1	           2606    17982    bill bill_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id_bill);
 8   ALTER TABLE ONLY public.bill DROP CONSTRAINT bill_pkey;
       public         postgres    false    199    199            3	           2606    17990    checkin checkin_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.checkin
    ADD CONSTRAINT checkin_pkey PRIMARY KEY (id_checkin);
 >   ALTER TABLE ONLY public.checkin DROP CONSTRAINT checkin_pkey;
       public         postgres    false    201    201            7	           2606    18006    checkout checkout_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.checkout
    ADD CONSTRAINT checkout_pkey PRIMARY KEY (id_checkout);
 @   ALTER TABLE ONLY public.checkout DROP CONSTRAINT checkout_pkey;
       public         postgres    false    205    205            +	           2606    17960    customer customer_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id_customer);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public         postgres    false    194    194            5	           2606    17998 "   customerbooked customerbooked_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.customerbooked
    ADD CONSTRAINT customerbooked_pkey PRIMARY KEY (id_cusbooked);
 L   ALTER TABLE ONLY public.customerbooked DROP CONSTRAINT customerbooked_pkey;
       public         postgres    false    203    203            9	           2606    18014    employee employee_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id_employee);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public         postgres    false    207    207            #	           2606    17925    report report_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.report
    ADD CONSTRAINT report_pkey PRIMARY KEY (id_report);
 <   ALTER TABLE ONLY public.report DROP CONSTRAINT report_pkey;
       public         postgres    false    186    186            -	           2606    17965    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id_room);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public         postgres    false    195    195            %	           2606    17929    status status_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id_status);
 <   ALTER TABLE ONLY public.status DROP CONSTRAINT status_pkey;
       public         postgres    false    187    187            /	           2606    17973    tobook tobook_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.tobook
    ADD CONSTRAINT tobook_pkey PRIMARY KEY (id_book);
 <   ALTER TABLE ONLY public.tobook DROP CONSTRAINT tobook_pkey;
       public         postgres    false    197    197            '	           2606    17933    useradmin useradmin_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.useradmin
    ADD CONSTRAINT useradmin_pkey PRIMARY KEY (id_user);
 B   ALTER TABLE ONLY public.useradmin DROP CONSTRAINT useradmin_pkey;
       public         postgres    false    188    188            �	   B   x�3��I-Ĩ�\���y)i�`*#�(���,�/��qY�)3,,��9Sr3� $W� ��O      �	      x������ � �      �	   $   x�3��s�4�4�444�q1�4����� QM%      �	   ,   x�3�,��K�,�/J�444c#N#c}}#CK$&W� P	�      �	   "   x�32��H��444�4464�44��\1z\\\ P�      �	      x������ � �      �	   �   x�mν
�0����*z6?MҎ�E�PC	�Hb��7�,R��qx�[l�Ѷ�Z)�.su��슾����9e�0�P���)��MaC��W�A3ܮ����k�R7R1.�0�6�ΔB�L@?M�����[���OWZ(�n����M�_�eCy7t@�      �	   $   x�3�44�70�720��444 NCS0����� mV�      �	   5   x�3�4�45 .#��4�4�4��LL3Ӝ�� ���m�Ď���� in*      �	   G   x�]��	�0���������_�!!bP�`0��ZM20H&&��"��$'	I"�+R�4�7��)�#"Ez      �	   $   x�3��42�4�40�70�720���P&W� i:�      �	      x�3�LL��̃�\1z\\\ 8Z     