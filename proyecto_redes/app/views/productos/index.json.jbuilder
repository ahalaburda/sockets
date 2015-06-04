json.array!(@productos) do |producto|
  json.extract! producto, :id, :descripion, :precio, :cantidad
  json.url producto_url(producto, format: :json)
end
