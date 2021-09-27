require 'infrastructure/address_repository_adapter'

module Domain
  module Ports
    module AddressRepositoryPort
      def find(address_id)
        Infrastructure::AddressRepositoryAdapter().find(address_id)
      end
    end
  end
end
