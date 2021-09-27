require_relative 'ports/address_repository_port'

module Domain
  class User
    def initialize(name:, age:, public_user:, address_id:, overrides = {})
      @name = name
      @age = age
      @public_user = public_user
      @address_id = address_id
      @address_repository_port = overrides.fetch(:address_repository_port) do
        Domain::Ports::AddressRepositoryPort.new
      end
    end

    def address
      # domain / business rule
      if public_user?
        @address ||= @address_repository_port.find(address_id)
      end
    end

    private

    def public_user?
      @public_user
    end
  end
end
