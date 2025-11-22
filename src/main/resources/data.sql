INSERT INTO employee (id, name, age)
VALUES ('1', 'Alice', '30');
INSERT INTO employee (id, name, age)
VALUES ('2', 'Bob', '25');
INSERT INTO employee (id, name, age)
VALUES ('3', 'Charlie', '35');
INSERT INTO employee (id, name, age)
VALUES ('4', 'Diana', '28');
-- ユーザーマスタ
INSERT INTO m_user (
    user_id,
    password,
    user_name,
    birthday,
    age,
    gender,
    department_id,
    role
  )
VALUES (
    'u001@co.jp',
    'password123',
    '山田太郎',
    '1990-01-15',
    34,
    1,
    101,
    'ADMIN'
  );
-- 部署マスタ
INSERT INTO m_department (department_id, department_name)
VALUES (101, '営業部'),
  (102, '開発部'),
  (103, '人事部');
-- 給料テーブル
INSERT INTO t_salary (user_id, year_month, salary)
VALUES ('u001@co.jp', '2024/01', 500000),
  ('u001@co.jp', '2024/02', 520000),
  ('u001@co.jp', '2024/03', 510000);