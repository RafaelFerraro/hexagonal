require 'domain/address'
# Framework implementation
# Redis database
# Relational database
# Service gateway
# Event publisher
# Whatever!
module Infrastructure
  class AddressRepositoryAdapter
    def initialize(api_token = nil)
      @api_token = api_token
    end

    def find(address_id)
      return Domain::Address.new(fetch_data)
    end

    private

    def fetch_data
      # fetch somewhere using the api token
    end
  end
end
