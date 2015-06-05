class CreateProductos < ActiveRecord::Migration
  def change
    create_table :productos do |t|
      t.string :descripion
      t.float :precio
      t.integer :cantidad

      t.timestamps null: false
    end
  end
end
