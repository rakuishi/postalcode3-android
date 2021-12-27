# http://zipcloud.ibsnet.co.jp/

require 'csv'
require 'sqlite3'
require 'nkf'

def nkf(str)
  NKF.nkf('-W -w -X -m0 -Z1 -h1', str)
end

APP_DB_PATH = "app/src/main/assets/app.db"

begin
  File.delete(APP_DB_PATH)
rescue
  p $!  # => #<Errno::ENOENT: No such file or directory @ unlink_internal - test.txt>
end

db = SQLite3::Database.new(APP_DB_PATH)

# AppDatabase_Impl#createAllTables
db.execute("CREATE TABLE IF NOT EXISTS `postal_codes` (`id` INTEGER NOT NULL, `code` TEXT NOT NULL, `prefecture` TEXT NOT NULL, `city` TEXT NOT NULL, `street` TEXT NOT NULL, `prefecture_pron` TEXT NOT NULL, `city_pron` TEXT NOT NULL, `street_pron` TEXT NOT NULL, PRIMARY KEY(`id`));")

id = 1

CSV.read('x-ken-all.csv', encoding: 'Shift_JIS:UTF-8', headers: false).each do |data|
  next if data[2].length == 0

  code = data[2]
  prefecture_pron = nkf(data[3])
  city_pron = nkf(data[4])
  street_pron = nkf(data[5])
  prefecture = data[6]
  city = data[7]
  street = data[8]

  db.execute('INSERT INTO postal_codes (id, code, prefecture, city, street, prefecture_pron, city_pron, street_pron) values (?, ?, ?, ?, ?, ?, ?, ?)', id, code, prefecture, city, street, prefecture_pron, city_pron, street_pron)

  id += 1

  print '*'
end

db.close
