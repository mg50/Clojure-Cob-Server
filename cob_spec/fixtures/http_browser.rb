require "rubygems"
gem 'httparty'
require "httparty"

module Fixtures

  class HttpBrowser
    attr_accessor :host, :port, :status, :data
    attr_reader :response

    def get(url)
      @response = HTTParty.get("http://#{@host}:#{@port}#{url}")
      @message = response.message
      @status =  response.code
      @data = response.body
    rescue Errno::ECONNREFUSED => e
      econnrefused e
    end

    def post(url)
      @response = HTTParty.post("http://#{@host}:#{@port}#{url}", :body => @data)
      @message = response.message
      @status = response.code
    rescue Errno::ECONNREFUSED => e
      econnrefused e
    end

    def put(url)
      @response = HTTParty.put("http://#{@host}:#{@port}#{url}", :body => @data)
      @message = response.message
      @status = response.code
    rescue Errno::ECONNREFUSED => e
      econnrefused e
    end

    def ok_response
      return @status == 200
    end

    def not_found_response
      return @status == 404
    end

    def moved_permanently_response
        puts @status
        return @status == 301
    end

    def body_has_content(content)
      @data.include? content
    end

    def body_has_directory_contents(directory)
      entries = Dir.entries(directory)
      entries.delete(".")
      entries.delete("..")
      entries.all? { |entry|
        @data.include? entry
      }
    end

    def body_has_file_contents(file)
      contents = File.open(file, 'rb') { |f| f.read }
      @data.include? contents
    end



    def get_without_following_redirect(url)
      begin
        HTTParty.get("http://#{@host}:#{@port}#{url}", :no_follow => true)
      rescue => e
        @response = e.response
        @message = response.message
        @status =  response.code.to_i
        @data = response.body
      end
    rescue Errno::ECONNREFUSED => e
      econnrefused e
    end

    private

      def econnrefused e
        @message = "#{e.message}.  Are you sure your server is running on http://#{@host}:#{@port}?"
      end
  end
end