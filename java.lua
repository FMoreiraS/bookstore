-- enable syntax highlight
vim.treesitter.start()

-- enable folds
vim.wo[0][0].foldexpr = "v:lua.vim.treesitter.foldexpr()"
vim.wo[0][0].foldmethod = 'expr'

-- enable indents
vim.bo.indentexpr = "v:lua.require'nvim-treesitter'.indentexpr()"
